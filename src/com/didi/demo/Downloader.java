package com.didi.demo;

import com.sun.istack.internal.NotNull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载实现类
 */
public class Downloader extends IDownloader {

    public Downloader(IDownloadStateListener listener) {
        super(listener);
    }

    public void startDownload(DownloadTask task) {
        if (task == null) {
            return;
        }

        String url = task.getUrl();
        String fileName = task.getFileName();
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
        }

        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                BufferedInputStream bufferedInputStream = null;
                BufferedOutputStream bufferedOutputStream = null;
                try {
                    HttpURLConnection connection = createConnection(url);

                    long contentLength = connection.getContentLength();
                    bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));
                    int len = 0;
                    long progress = 0;
                    byte[] buffer = new byte[1024];
                    while ((len = bufferedInputStream.read(buffer)) != -1) {
                        bufferedOutputStream.write(buffer, 0, len);
                        progress += len;
                        notifyer().onProgress(contentLength, progress);
                    }
                    notifyer().onSuccess();
                } catch (IOException e) {
                    e.printStackTrace();
                    notifyer().onFailed(e.getMessage());
                } finally {
                    try {
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                    } catch (IOException e) {

                    }
                }
            }
        });
    }

    private static HttpURLConnection createConnection(@NotNull String downloadUrl) throws IOException {
        URL url = new URL(downloadUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Content-Type", "application/octet-stream");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(10000);
        connection.connect();
        return connection;
    }
}
