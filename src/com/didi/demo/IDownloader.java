package com.didi.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by yangbing 2019-08-10 12:24
 */
abstract class IDownloader {

    static ExecutorService mExecutorService = Executors.newCachedThreadPool();

    private IDownloadStateListener listener;

    public IDownloader(IDownloadStateListener listener) {
        this.listener = listener;
    }

    protected IDownloadStateListener notifyer() {
        return listener;
    }

}
