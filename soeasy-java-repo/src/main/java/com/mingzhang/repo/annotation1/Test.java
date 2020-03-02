package com.mingzhang.repo.annotation1;

import com.mingzhang.repo.annotation1.dao.IPeople;

import java.util.List;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-02-27 17:07
 */
public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // 获取特定包下所有的类(包括接口和类)
        List<Class<?>> clsList = ClassUtil.getAllClassByPackageName(IPeople.class.getPackage());
        //输出所有使用了特定注解的类的注解值
        System.out.println(clsList);

        for (int i = 0; i < clsList.size(); i++) {
            Class<?> aClass = clsList.get(i);
            if (aClass.getPackage() == IPeople.class.getPackage()) break;
            IPeople o = (IPeople) aClass.newInstance();
            o.say();
        }

        AnnotationUtil.validAnnotation(clsList);
    }

}