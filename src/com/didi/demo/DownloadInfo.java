package com.didi.demo;

import java.io.Serializable;

public class DownloadInfo implements Serializable {
    private String id;
    private String name;
    private String downloadUrl;
    private long totalSize;
    private long progressSize;
    private String fileName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public long getProgressSize() {
        return progressSize;
    }

    public void setProgressSize(long progressSize) {
        this.progressSize = progressSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
