package com.mingzhang.repo.annotation1;

import java.lang.reflect.Method;
import java.util.List;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-02-27 17:06
 */
public class AnnotationUtil {

    public static void validAnnotation(List<Class<?>> clsList) {
        if (clsList != null && clsList.size() > 0) {
            for (Class<?> cls : clsList) {
                //获取类中的所有的方法
                Method[] methods = cls.getDeclaredMethods();
                System.out.println(methods.length);
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        PeopleAnnotion peopleAnnotion = (PeopleAnnotion) method.getAnnotation(PeopleAnnotion.class);
                        if (peopleAnnotion != null) {
                            //可以做权限验证
                            System.out.println(peopleAnnotion.say());
                        }
                    }
                }
            }
        }
    }

}
