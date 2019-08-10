package com.didi.demo;

import com.sun.istack.internal.NotNull;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class DownloadQueue implements IDownloadQueue {

    private final static int mMaxRunningSize = 2;

    private LinkedHashMap<String, DownloadTask> mPendingQueue;
    private LinkedHashMap<String, DownloadTask> mRuningQueue;

    public DownloadQueue() {
        mPendingQueue = new LinkedHashMap<>();
        mRuningQueue = new LinkedHashMap<>();
    }

    private void triggerSchdule() {
        if (mPendingQueue.size() > 0 && mRuningQueue.size() < mMaxRunningSize) {
            new ScheduleRunable().run();
        }
    }

    @Override
    public void enqueue(DownloadTask task) {
        if (verifyTask(task)) {
            task.setState(DownloadState.QUEUED);
            mPendingQueue.put(task.getId(), task);
            triggerSchdule();
        }
    }

    @Override
    public void dequeue(DownloadTask task) {
        String id = task.getId();
        if (queryById(id) != null) {
            if (mPendingQueue.containsKey(id)) {
                mPendingQueue.remove(id);
            }
            if (mRuningQueue.containsKey(id)) {
                mRuningQueue.remove(id);
            }
            triggerSchdule();
        }
    }

    private DownloadTask queryById(String id) {
        if (mPendingQueue.containsKey(id)) {
            return mPendingQueue.get(id);
        }
        if (mRuningQueue.containsKey(id)) {
            return mRuningQueue.get(id);
        }
        return null;
    }

    private void startDownload(DownloadTask task) {
        if (verifyTask(task)) {
            mRuningQueue.put(task.getId(), task);
            mPendingQueue.remove(task.getId());
            new Downloader(task).startDownload(task);
            task.onStart();
        }
    }

    class ScheduleRunable implements Runnable {
        @Override
        public void run() {
            DownloadTask task;
            Iterator<String> iterator = mPendingQueue.keySet().iterator();
            while (iterator.hasNext() && !isReachedMax()) {
                String id = iterator.next();
                task = mPendingQueue.get(id);
                task.bindQueue(DownloadQueue.this);
                mPendingQueue.remove(id);
                startDownload(task);
            }
        }
    }

    private boolean isReachedMax() {
        return mRuningQueue.size() >= mMaxRunningSize;
    }

    private boolean verifyTask(@NotNull DownloadTask task) {
        String id = task.getId();
        // 已经存在
        if (queryById(id) != null) {
            return false;
        }

        // 网络

        // 存储

        return true;
    }
}
