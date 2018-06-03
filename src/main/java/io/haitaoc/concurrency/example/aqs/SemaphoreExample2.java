package io.haitaoc.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample2 {

    private final static  int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);


        /**
         * 200个请求，每次并发数只有3个
         */
        for (int i = 0; i <threadCount ; i++) {
            final int threadNum = i;
            exec.execute(()->{
                try {
                    semaphore.acquire(3);        //获取多个许可
                    test(threadNum);
                    semaphore.release(3);        //释放多个许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        System.out.println("finish");
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        System.out.println(threadNum);
        Thread.sleep(1000);
    }
}
