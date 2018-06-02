package io.haitaoc.concurrency.atomic;

//import lombok.Getter;


import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    public volatile int count = 100;

    public int getCount(){
        return count;
    }

    private static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {
        if (updater.compareAndSet(example5, 100, 120)) {
            System.out.println("update success 1 "+ example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)) {
            System.out.println("update success 2 "+ example5.getCount());
        } else {
            System.out.println("update failed "+ example5.getCount());
        }
    }
}

