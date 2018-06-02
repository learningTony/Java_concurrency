package io.haitaoc.concurrency.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicExample4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);       // count = 2
        count.compareAndSet(0,1);       // no
        count.compareAndSet(1,3);       // no
        count.compareAndSet(2,4);       // 4
        count.compareAndSet(3,5);       // no
        System.out.println(count.get());
    }
}
