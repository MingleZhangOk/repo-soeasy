/**
 *
 */
package com.mingzhang.api.util;

import java.util.UUID;

public class RandomUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
