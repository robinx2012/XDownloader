package com.didi.demo;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        DownloadInfo downloadInfo = new DownloadInfo();
        downloadInfo.setDownloadUrl("https://dldir1.qq.com/qqfile/qq/PCQQ9.1.6/25786/QQ9.1.6.25786.exe");
        downloadInfo.setId("111");
        downloadInfo.setName("WebStorm-2019.2.dmg");
        downloadInfo.setFileName("WebStorm-2019.1.dmg");

        DownloadInfo downloadInfo1 = new DownloadInfo();
        downloadInfo1.setDownloadUrl("https://dldir1.qq.com/qqfile/qq/PCQQ9.1.6/25786/QQ9.1.6.25786.exe");
        downloadInfo1.setId("222");
        downloadInfo1.setName("WebStorm-2019.2.dmg");
        downloadInfo1.setFileName("WebStorm-2019.2.dmg");

        DownloadInfo downloadInfo2 = new DownloadInfo();
        downloadInfo2.setDownloadUrl("https://dldir1.qq.com/qqfile/qq/PCQQ9.1.6/25786/QQ9.1.6.25786.exe");
        downloadInfo2.setId("333");
        downloadInfo2.setName("WebStorm-2019.2.dmg");
        downloadInfo2.setFileName("WebStorm-2019.3.dmg");

        DownloadInfo downloadInfo3 = new DownloadInfo();
        downloadInfo3.setDownloadUrl("https://dldir1.qq.com/qqfile/qq/PCQQ9.1.6/25786/QQ9.1.6.25786.exe");
        downloadInfo3.setId("444");
        downloadInfo3.setName("WebStorm-2019.2.dmg");
        downloadInfo3.setFileName("WebStorm-2019.4.dmg");


        DownloadTask task1 = new DownloadTask(downloadInfo);
        DownloadTask task2 = new DownloadTask(downloadInfo1);
        DownloadTask task3 = new DownloadTask(downloadInfo2);
        DownloadTask task4 = new DownloadTask(downloadInfo3);

        DownloadQueue queue = new DownloadQueue();
        queue.enqueue(task1);
        queue.enqueue(task2);
        queue.enqueue(task3);
        queue.enqueue(task4);

        Thread.sleep(3000);

        task1.onPause();

        Thread.sleep(3000);

        task1.onResume();

        Thread.sleep(3000);

        task2.onPause();

        Thread.sleep(3000);

        task2.onResume();

        Thread.sleep(3000);

        task3.onPause();
        task4.onPause();


    }
}
