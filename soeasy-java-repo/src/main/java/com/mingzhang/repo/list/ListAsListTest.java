package com.mingzhang.repo.list;

import java.util.Arrays;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @Description
 * @Classname ListAsListTest
 * @date 2020-06-02 16:45
 */
public class ListAsListTest {
    public static void main(final String[] args) {
        System.out.println(
                Arrays.asList(new String[]{"a", "b"}));

        System.out.println(
                Arrays.asList(new Integer[]{1, 2}));

        System.out.println(
                Arrays.asList(new int[]{1, 2}));

        System.out.println(
                Arrays.asList(new String[]{"a", "b"}, "c"));
    }
}
