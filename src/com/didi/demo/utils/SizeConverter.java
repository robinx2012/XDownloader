package com.didi.demo.utils;

/**
 * Create by yangbing 2019-08-10 12:35
 */
public class SizeConverter {

    public static String convert(long sizeByte) {
        if (sizeByte < 1024) {
            return sizeByte + "Byte";
        } else if (sizeByte < 1024 * 1024) {
            return sizeByte * 1.0f / 1024 + "KB";
        } else {
            return sizeByte * 1.0f / 1024 / 1024 + "MB";
        }
    }
}
