/*
package cn.com.frms.com.mingzhang.table.tests.mainFile;

import com.basic.core.util.FileUtil;
import sun.tools.jar.Main;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

*/
/**
 * desc:自定义的类加载器，用于实现类的动态加载
 *//*

public class MyClassLoader extends ClassLoader {


    //类路径
    private static String classPath;

    private static String jarPrefix;

    private static StringBuilder jarAbsolutePath;

    static {
        classPath = MyClassLoader.class.getClassLoader().getResource("").getPath();
        classPath = !classPath.startsWith("/") ? classPath : classPath.substring(1);//去掉开始位置的/
        classPath = classPath.endsWith(File.separator) ? classPath : classPath + File.separator;
        jarPrefix = classPath.substring(0, classPath.lastIndexOf("classes")) + File.separator + "lib" + File.separator;
        jarAbsolutePath = new StringBuilder().append(jarPrefix)
                .append("hibernate-core-4.2.0.Final.jar;")
                .append(jarPrefix).append("hibernate-jpa-2.0-api-1.0.1.Final.jar;")
                .append(jarPrefix).append("validation-api-1.0.0.GA.jar;");
    }

    */
/**
     * 如果父的类加载器中都找不到name指定的类，
     * 就会调用这个方法去从磁盘上加载一个类
     *
     * @param name 类的全限定包名 不带后缀  例如com.test.Notice  而不要写成com.test.Notice.java
     * @return
     * @throws java.io.IOException
     *//*

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = null;
        Class<?> clazz = null;
        try {
            //加载类的字节码
            classBytes = loadClassBytes(name);
            //将字节码交给JVM
            clazz = defineClass(name, classBytes, 0, classBytes.length);
            if (clazz == null) {
                throw new ClassNotFoundException(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    */
/**
     * 加载类的字节码
     *
     * @param name 类的全限定包名 不带后缀  例如com.test.Notice  而不要写成com.test.Notice.java
     * @return
     * @throws java.io.IOException
     *//*

    private byte[] loadClassBytes(String name) throws IOException {
        String classPackageName = name.replace(".", File.separator) + ".class";
        String classAbsolutePath = classPath + classPackageName;
        //编译java文件
        javac(name);
        byte[] bytes = Files.readAllBytes(Paths.get(classAbsolutePath));
        return bytes;
    }

    */
/**
     * 指定的类的class是否存在
     *
     * @param name
     * @return
     * @throws IOException
     *//*

    public static boolean isClassExist(String name) throws IOException {
        String classPackageName = name.replace(".", File.separator) + ".class";
        return FileUtil.isExists(classPath + classPackageName) ? true : false;
    }

    */
/**
     * 指定的类是否存在
     *
     * @param name
     * @return
     * @throws IOException
     *//*

    public static boolean isJavaExist(String name) throws IOException {
        String classPackageName = name.replace(".", File.separator) + ".java";
        return FileUtil.isExists(classPath + classPackageName) ? true : false;
    }

    */
/**
     * 编译java类
     * 使用Runtime执行javac命令
     *
     * @param name 类的全限定包名 不带后缀  例如com.test.Notice  而不要写成com.test.Notice.java
     * @throws java.io.IOException
     *//*

    public static void javac(String name) throws IOException {
        String javaPackageName = name.replace(".", File.separator) + ".java";
        String javaAbsolutePath = classPath + javaPackageName;
        Process process = Runtime.getRuntime().exec("javac -classpath " + jarAbsolutePath + " " + javaAbsolutePath);
        try {
            InputStream errorStream = process.getErrorStream();
            InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            int exitVal = process.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    */
/**
     * 编译java类
     * 使用rt.jar中的javax.tools包提供的编译器
     *
     * @param name 类的全限定包名 不带后缀  例如com.test.Notice  而不要写成com.test.Notice.java
     * @throws java.io.IOException
     *//*

    public static void compiler(String name) throws IOException {
        String javaPackageName = name.replace(".", File.separator) + ".java";
        String javaAbsolutePath = classPath + javaPackageName;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, "-encoding", "UTF-8", "-classpath", jarAbsolutePath.toString(), javaAbsolutePath);
    }

    */
/**
     * 动态编译一个java源文件并加载编译生成的class
     *
     * @param name 类的全限定包名 不带后缀  例如com.test.Notice  而不要写成com.test.Notice.java
     * @throws java.io.IOException
     *//*

    public static Class<?> dynamicLoadClass(String name) throws IOException, ClassNotFoundException {
        if (!isClassExist(name)) {
            compiler(name);
        }
        if (isJavaExist(name)) {
            if (!FileUtil.destroyFile(classPath + name.replace(".", File.separator) + ".java")) {
                System.out.println("========================================>>>>删除源文件失败!");
            }
        }
        return Class.forName(name);
    }

    public static void main(String[] args) {

    }


}
*/
