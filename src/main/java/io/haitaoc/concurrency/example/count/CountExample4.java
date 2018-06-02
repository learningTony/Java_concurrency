package io.haitaoc.concurrency.example.count;



import io.haitaoc.concurrency.annotation.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class CountExample4 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程总数
    public static int threadTotal = 200;

    public static volatile int count = 0;

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

    /**
     *  当两个线程同时执行了第一步, 就都读到了count的值, 比如是5
     *  两个线程同时执行了+1操作并写回主存，这样就丢了一次+1的操作，结果是6问不是5
     */
    public static void add(){
        // 1. 去除内存中的count值
        // 2. +1
        // 3. count写回主存
        count++;
    }
}
