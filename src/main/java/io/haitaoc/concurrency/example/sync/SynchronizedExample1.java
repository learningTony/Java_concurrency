package io.haitaoc.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized示例
 */
public class SynchronizedExample1 {

    public void test1(int j) {
        // 修饰一个代码块, 作用于当前调用对象，不同对象间不影响
        synchronized (this) {             // 作用范围是 大括号括起来的部分, 作用对象是调用这部分代码的对象
            for (int i = 0; i < 10; i++) {
                System.out.println("test1 - " + i+" "+j);
            }
        }                               // 作用范围结束
    }

    // 修饰一个方法, 作用于当前调用对象，不同对象间不影响
    public synchronized void test2(int j) {      // 作用范围是整个方法, 作用调用此方法的对象
        for (int i = 0; i < 10; i++) {
            System.out.println("test2 - " + i+" "+j);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        // 使用线程池, 相当于分别启动两个进程来执行test1()
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test2(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }
}
