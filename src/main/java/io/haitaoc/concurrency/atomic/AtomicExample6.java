package io.haitaoc.concurrency.atomic;

import io.haitaoc.concurrency.annotation.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@ThreadSafe
public class AtomicExample6 {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

        // 请求总数
        public static int clientTotal = 5000;

        // 同时并发执行的线程总数
        public static int threadTotal = 200;

      //  public static AtomicLong count = new AtomicLong(0);

        public static void main(String[] args) throws InterruptedException {
            ExecutorService executorService = Executors.newCachedThreadPool();
            final Semaphore semaphore = new Semaphore(threadTotal);
            final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
            for (int i = 0; i <clientTotal ; i++) {
                executorService.execute(()->{
                    try {
                        semaphore.acquire();       // 同时200个线程进行操作
                        test();
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();                // 所有线程执行任务完毕才打印count
            executorService.shutdown();
            System.out.println("isHappened "+ isHappened.get());
        }

        private static void test(){
            if(isHappened.compareAndSet(false,true)){
                System.out.println("execute");
            }
        }

}
