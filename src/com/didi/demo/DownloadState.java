package com.didi.demo;

public class DownloadState {
    private static final int BASE           = 0x01;

    public static final int UNQUEUE         = BASE + 2;
    public static final int QUEUED          = BASE + 3;
    public static final int DOWNLOADING     = BASE + 4;
    public static final int PAUSED          = BASE + 5;
    public static final int CANCELED        = BASE + 6;
    public static final int SUCCESS         = BASE + 7;
    public static final int FAILED          = BASE + 8;
}
