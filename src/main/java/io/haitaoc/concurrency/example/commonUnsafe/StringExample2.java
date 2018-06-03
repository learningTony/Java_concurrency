package io.haitaoc.concurrency.example.commonUnsafe;

import io.haitaoc.concurrency.annotation.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class StringExample2 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程总数
    public static int threadTotal = 200;

    public static StringBuffer StringBuffer = new StringBuffer();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i <clientTotal ; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();       // 同时200个线程进行操作
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();                // 所有线程执行任务完毕才打印count
        executorService.shutdown();
        System.out.println(StringBuffer.length());
    }

    public static void update(){
        StringBuffer.append("1");
    }

}
