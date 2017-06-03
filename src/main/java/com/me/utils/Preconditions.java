package com.me.utils;

/**
 * @author gaoxin.wei
 */
public class Preconditions {

    public static void checkState(boolean b) {
        if (!b) {
            throw new RuntimeException("check state failed");
        }
    }
}
