package com.mingzhang.parser.test;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 17:32
 */
public class JavaBeanDemo {

    public static String upper(String abc) {
        return abc.toUpperCase();
    }

    public boolean anyContains(String str, String searchStr) {
        char[] s = str.toCharArray();
        for (char c : s) {
            if (searchStr.contains(c + "")) {
                return true;
            }
        }
        return false;
    }

    public int getMax(Integer... a) {
        if (a.length == 1) {
            return a[0];
        } else if (a.length > 1) {
            return getLoopMax(a);
        } else {
            return 0;
        }
    }

    private int getLoopMax(Integer... a) {
        if (a.length == 2) {
            return Math.max(a[0], a[1]);
        } else {
            Integer[] newA = new Integer[a.length - 1];
            for (int i = 0; i < a.length - 1; i++) {
                newA[i] = a[i];
            }
            int loopMax = getLoopMax(newA);
            return Math.max(a[a.length - 1], loopMax);
        }
    }

    public static void main(String[] args) {
        JavaBeanDemo javaBeanDemo = new JavaBeanDemo();
        System.out.println(javaBeanDemo.getMax(2, 2, 2, 2, 2, 3, 4, 5, 6, 1, 9, 123, 12, 32, 29, 1));
    }

}
