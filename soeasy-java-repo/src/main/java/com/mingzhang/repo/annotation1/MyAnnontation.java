package com.mingzhang.repo.annotation1;

import java.lang.annotation.*;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-02-27 16:51
 */

@Target({ElementType.FIELD, ElementType.METHOD}) //声明自定义的注解使用在方法上
@Retention(RetentionPolicy.RUNTIME)//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Documented
public @interface MyAnnontation {
    String WayCode() default "none";

    String WayName() default "空";
}
