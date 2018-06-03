package io.haitaoc.concurrency.example.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {

    private final static  int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i <threadCount ; i++) {
            final int threadNum = i;
            exec.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(); // 确保上述过程执行完才执行后续语句
        System.out.println("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        System.out.println(threadNum);
        Thread.sleep(100);
    }
}
