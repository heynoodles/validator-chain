package com.me.utils;

/**
 * @author gaoxin.wei
 */
public class StringUtils {

    public static  boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str) || "".equals(str.trim());
    }
}
