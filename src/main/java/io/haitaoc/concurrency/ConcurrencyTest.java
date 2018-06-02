package io.haitaoc.concurrency;



import io.haitaoc.concurrency.annotation.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class ConcurrencyTest {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程总数
    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i <clientTotal ; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();       // 同时200个线程进行操作
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();                // 所有线程执行任务完毕才打印count
        executorService.shutdown();
        System.out.println(count);
    }

    public static void add(){
        count++;
    }
}
