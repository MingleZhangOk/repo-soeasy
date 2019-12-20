package com.mingzhang.repo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2019-12-21 0:17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HelloAnnotation {
    String say() default "Hi";
}
