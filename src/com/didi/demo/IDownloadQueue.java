package com.didi.demo;

/**
 * Create by yangbing 2019-08-09 20:02
 */
public interface IDownloadQueue {

    /**
     * 入队
     *
     * @param task
     */
    void enqueue(DownloadTask task);

    /**
     * 出队
     *
     * @param task
     */
    void dequeue(DownloadTask task);

}
