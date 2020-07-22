package com.mingzhang.repo.hotload.dynamic;

import java.io.File;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

//import com.bojoy.agent.JavaDynAgent;
import com.sun.tools.attach.VirtualMachine;


/**
 * @author d
 */
public class DynamicEngine {
    //单例
    private static DynamicEngine ourInstance = new DynamicEngine();
    private static DynamicClassLoader dynamicClassLoader;

    private static String jarPath;
    private static VirtualMachine vm;
    private static String pid;
    private URLClassLoader parentClassLoader;
    private String classpath;

    static {
        jarPath = getJarPath();
        // 当前进程pid
        String name = ManagementFactory.getRuntimeMXBean().getName();
        pid = name.split("@")[0];
        System.out.println("------------pid-------" + pid);
        System.out.println("------------jarPath-----------" + jarPath);
    }


    public static DynamicEngine getInstance() {
        return ourInstance;
    }

    private DynamicEngine() {
        //获取类加载器
        this.parentClassLoader = (URLClassLoader) this.getClass().getClassLoader();
        if (dynamicClassLoader == null) {
            synchronized (DynamicEngine.class) {
                if (dynamicClassLoader == null) {
                    dynamicClassLoader = new DynamicClassLoader(parentClassLoader);
                }
            }
        }
        //创建classpath
        this.buildClassPath();
    }

    private void buildClassPath() {
        this.classpath = null;
        StringBuilder sb = new StringBuilder();
        for (URL url : this.parentClassLoader.getURLs()) {
            String p = url.getFile();
            sb.append(p).append(File.pathSeparator);
        }
        this.classpath = sb.toString();
    }

    private static void init() throws Exception {
        // 虚拟机加载
        vm = VirtualMachine.attach(pid);
        System.out.println("代理jar路径：" + jarPath + File.separator + "javaagent.jar");
        vm.loadAgent(jarPath + File.separator + "javaagent.jar");

//        Instrumentation instrumentation = JavaDynAgent.getInstrumentation();
//        if (instrumentation == null) {
//            throw new Exception("无法获取代理Instrumentation");
//        }

    }

    private static void destroy() throws Exception {
        if (vm != null) {
            vm.detach();
        }
    }

    public static String getJarPath() {
        // StringUtils是jar文件内容
        URL url = VirtualMachine.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
            // 截取路径中的jar包名
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }

        File file = new File(filePath);

        filePath = file.getAbsolutePath();//得到windows下的正确路径
        return filePath;
    }

    public Object loadClass(String fullClassName) throws Exception {
        Object instance = null;
        try {
            Class<?> clazz = dynamicClassLoader.loadClass(fullClassName);
            instance = clazz.newInstance();
            return instance;
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * @param fullClassName 类名
     * @param javaCode      类代码
     * @return Object
     * @throws Exception
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @MethodName    : 编译java代码到Object
     * @Description    : TODO
     */
    public Object compiler(String fullClassName, String javaCode) throws Exception {
        System.out.println("编译java文件");
        Object instance = null;
        //获取系统编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 建立DiagnosticCollector对象
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        // 建立用于保存被编译文件名的对象
        // 每个文件被保存在一个从JavaFileObject继承的类中
        ClassFileManager fileManager = new ClassFileManager(compiler.getStandardFileManager(diagnostics, null, null));

        List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
        CharSequenceJavaFileObject sourceFile = new CharSequenceJavaFileObject(fullClassName, javaCode);
        jfiles.add(sourceFile);

        //使用编译选项可以改变默认编译行为。编译选项是一个元素为String类型的Iterable集合
        List<String> options = new ArrayList<String>();
        options.add("-encoding");
        options.add("UTF-8");
        options.add("-classpath");
        options.add(this.classpath);

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, jfiles);
        // 编译源程序
        boolean success = task.call();
        sourceFile.delete();
        sourceFile = null;
        jfiles = null;
        options = null;
        task = null;
        compiler = null;
        if (success) {
            //如果编译成功，用类加载器加载该类
            JavaClassObject jco = fileManager.getJavaClassObject();
            Class<?> clazz = null;
            try {
                //第一次编译加载
                clazz = dynamicClassLoader.loadClass(fullClassName, jco);
                jco.delete();
                jco = null;
            } catch (Throwable e) {
                //修改java代码后，需要重新加载
                //LOG.error("加载class有问题-------");
                try {
                    init();
                    ClassDefinition definition = new ClassDefinition(dynamicClassLoader.loadClass(fullClassName), jco.getBytes());
//                    Instrumentation instrumentation = JavaDynAgent.getInstrumentation();
//                    instrumentation.redefineClasses(definition);
                    clazz = dynamicClassLoader.loadClass(fullClassName);
                    //LOG.info("重新定义class完成-------");
                } catch (Throwable ee) {
                    //LOG.error(ee);
                    throw new Exception("重新定义class异常");
                } finally {
                    jco.delete();
                    jco = null;
                    destroy();
                }
            }
            instance = clazz.newInstance();

        } else {
            //如果想得到具体的编译错误，可以对Diagnostics进行扫描
            fileManager.close();
            String error = "";
            for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                error = diagnostic.getMessage(null);
                if (error == null && "".equalsIgnoreCase(error) && error.length() == 0) {
                    error = "动态编译java异常:[" + error + "]";
                    throw new Exception(error);
                }
            }
            diagnostics = null;
            instance = error;
        }
        fileManager.close();
        return instance;
    }
}