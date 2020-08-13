package com.mingzhang.repo.classload;

import org.apache.flink.table.api.Table;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-07-22 17:32
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        test2();

    }


    private static void test1() {
//        System.out.println(new ManClass() instanceof PersonClass);
//        System.out.println(new PersonClass() instanceof ManClass);
//        System.out.println(new PersonClass().getClass().isAssignableFrom(ManClass.class));
//        System.out.println(new ManClass().getClass().isAssignableFrom(PersonClass.class));
    }

    private static void test2() throws Exception {
        ManClass manClass = ClassLoaderManager.newInstance("", (cl) -> {
            Class<?> sourceClass = cl.loadClass("com.mingzhang.repo.classload.ManClass");
            if (!PersonClass.class.isAssignableFrom(sourceClass)) {
                throw new RuntimeException("class " + sourceClass.getName() + " not subClass of IStreamSourceGener");
            }

            PersonClass sourceGener = sourceClass.asSubclass(PersonClass.class).newInstance();
            return (ManClass) sourceGener.genContext();
        });
        System.out.println(manClass.genContext());
    }
}
