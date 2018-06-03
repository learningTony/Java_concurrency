package io.haitaoc.concurrency.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample1 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService  = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void race(int threadNum)throws Exception{
        Thread.sleep(1000);
        System.out.println(threadNum+" is ready");
        barrier.await();        // 达到数目后执行后续语句
        System.out.println(threadNum+" continue");
    }
}
