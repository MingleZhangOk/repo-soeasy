package com.mingzhang.repo.hotload.dynamic;

public class Test {

	public static void main(String[] args) throws Exception {
		DynamicEngine dynamicEngine = DynamicEngine.getInstance();
		String className = "Hello";
		String javaCode = "public class Hello { public void sayHello(){System.out.println(\"####\");}}";
		
		for(int i=0;i<10;i++){
			String str = javaCode.replace("####", "hello world"+i);
			Object obj = dynamicEngine.compiler(className,str);
			obj.getClass().getMethod("sayHello").invoke(obj);
		}
		
		
	}
}
