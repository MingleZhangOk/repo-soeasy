package com.mingzhang.repo.classload;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-07-22 17:32
 */
public class ManClass implements PersonClass<String> {
    @Override
    public String genContext() {
        return "test";
    }
}
