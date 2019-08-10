package com.didi.demo;

public interface IDownloadStateListener {

    /**
     * 入队
     */
    void onEnqueue();

    /**
     * 开始下载
     */
    void onStart();

    /**
     * 下载中
     *
     * @param total
     * @param current
     */
    void onProgress(long total, long current);

    /**
     * 暂停
     */
    void onPause();

    /**
     * 恢复
     */
    void onResume();

    /**
     * 取消
     */
    void onCancel();

    /**
     * 下载成功
     */
    void onSuccess();

    /**
     * 下载失败
     *
     * @param reson
     */
    void onFailed(String reson);

}
