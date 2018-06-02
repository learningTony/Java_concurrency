package io.haitaoc.concurrency.example.publish;

import io.haitaoc.concurrency.annotation.NotThreadSafe;

@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    /**
     * 通过new UnsafePublish()发布了一个UnsafePublish类的实例
     * 通过实例的public方法得到了私有域states数组的引用
     * 可以在其他任何线程里修改这个数组里的值
     * 这样在其他线程中想使用states数组时，它的值是不完全确定的
     * 因此这样发布的对象是线程不安全的，因为无法保证是否有其他线程对数组里的值进行了修改
     * 方法
     * @param args
     */
    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        for (String i : unsafePublish.getStates()) {
            System.out.print(i+" ");
        }
        unsafePublish.getStates()[0] = "d";
        System.out.println();
        for (String i : unsafePublish.getStates()) {
            System.out.print(i+" ");
        }
    }
}
