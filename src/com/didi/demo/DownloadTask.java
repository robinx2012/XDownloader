package com.didi.demo;

import com.didi.demo.utils.LogUtil;
import com.didi.demo.utils.SizeConverter;

import java.util.ArrayList;

public class DownloadTask implements IDownloadStateListener {

    private final ArrayList<IDownloadStateListener> mStateListeners;
    private final DownloadInfo mDownloadInfo;
    private int mDownloadState = DownloadState.UNQUEUE;
    private IDownloadQueue mQueue;

    public DownloadTask(DownloadInfo downloadInfo) {
        mStateListeners = new ArrayList<>();
        this.mDownloadInfo = downloadInfo;
    }

    public void addStateChangedListener(IDownloadStateListener listener) {
        if (!mStateListeners.contains(listener)) {
            mStateListeners.add(listener);
        }
    }

    public void bindQueue(IDownloadQueue queue) {
        this.mQueue = queue;
    }

    @Override
    public void onEnqueue() {
        setState(DownloadState.QUEUED);
        notifyAllListener(null, null);
        LogUtil.d(getClass().getName(), "DownaloadTask[" + getId() + "]" + "onEnqueue ");
    }

    @Override
    public void onStart() {
        setState(DownloadState.DOWNLOADING);
        notifyAllListener(null, null);
        LogUtil.d(getClass().getName(), "DownaloadTask[" + getId() + "]" + "onStart ");
    }

    @Override
    public void onProgress(long total, long current) {
        notifyAllListener(total, current);
//        LogUtil.d(getClass().getName(), "DownaloadTask[" + getId() + "]" + "onProgress "
//                + SizeConverter.convert(current) + "/" + SizeConverter.convert(total));
    }

    @Override
    public void onSuccess() {
        setState(DownloadState.SUCCESS);
        notifyAllListener(null, null);
        mQueue.dequeue(this);
        LogUtil.d(getClass().getName(), "DownaloadTask[" + getId() + "]" + "onSuccess");
    }

    @Override
    public void onFailed(String reson) {
        setState(DownloadState.FAILED);
        notifyAllListener(reson, null);
        mQueue.dequeue(this);
        LogUtil.d(getClass().getName(), "DownaloadTask[" + getId() + "]" + "onFailed " + reson);
    }

    @Override
    public void onPause() {
        setState(DownloadState.PAUSED);
        notifyAllListener(null, null);
        mQueue.dequeue(this);
        LogUtil.d(getClass().getName(), "DownaloadTask[" + getId() + "]" + "onPause ");
    }

    @Override
    public void onResume() {
        setState(DownloadState.QUEUED);
        notifyAllListener(null, null);
        mQueue.enqueue(this);
        LogUtil.d(getClass().getName(), "DownaloadTask[" + getId() + "]" + "onResume ");
    }

    @Override
    public void onCancel() {
        setState(DownloadState.CANCELED);
        notifyAllListener(null, null);
        mQueue.dequeue(this);
        LogUtil.d(getClass().getName(), "DownaloadTask[" + getId() + "]" + "onCancel ");
    }

    String getId() {
        return mDownloadInfo.getId();
    }

    String getUrl() {
        return mDownloadInfo.getDownloadUrl();
    }

    String getFileName() {
        return mDownloadInfo.getFileName();
    }

    void setState(int state) {
        this.mDownloadState = state;
    }

    private void notifyAllListener(Object arg1, Object arg2) {
        int state = mDownloadState;
        int count = mStateListeners.size();
        if (state == DownloadState.QUEUED) {
            for (IDownloadStateListener mStateListener : mStateListeners) {
                mStateListener.onStart();
            }
        } else if (state == DownloadState.DOWNLOADING) {
            for (IDownloadStateListener mStateListener : mStateListeners) {
                mStateListener.onProgress((long) arg1, (long) arg2);
            }
        } else if (state == DownloadState.SUCCESS) {
            for (IDownloadStateListener mStateListener : mStateListeners) {
                mStateListener.onSuccess();
            }
        } else if (state == DownloadState.PAUSED) {
            for (IDownloadStateListener mStateListener : mStateListeners) {
                mStateListener.onPause();
            }
        } else if (state == DownloadState.FAILED) {
            for (IDownloadStateListener mStateListener : mStateListeners) {
                mStateListener.onFailed((String) arg1);
            }
        } else if (state == DownloadState.CANCELED) {
            for (IDownloadStateListener mStateListener : mStateListeners) {
                mStateListener.onCancel();
            }
        }
    }
}
