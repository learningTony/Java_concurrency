package io.haitaoc.concurrency.example.singleton;

import io.haitaoc.concurrency.annotation.NotRecommend;
import io.haitaoc.concurrency.annotation.NotThreadSafe;
import io.haitaoc.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式 》 双重同步锁单例模式
 * 用volatile修饰单例对象   不会出现指令重排的问题
 */
@ThreadSafe
@NotRecommend
public class SingletonExample5 {

    // 首先要保证这个类不能随便被new一个新的对象出来，所以将构造函数私有化
    private SingletonExample5(){

    }

    // 定义一个单例对象 volatile + 双重检测机制 -> 禁止指令重排(原因是这里面的instance的写操作)
    private volatile static SingletonExample5 instance = null;

    // 1. memeory = allocate()  分配对象的内存空间
    // 2. contructInstance()   初始化对象
    // 3. instance=memory 设置instance指向刚分配的内存

    // 静态的工厂方法获取一个单例对象
    public static SingletonExample5 getInstance(){
        if(instance==null){         // 双重检测机制       // B
            synchronized (SingletonExample5.class){     // 同步锁
                if(instance==null){
                    instance = new SingletonExample5();     // A - 3
                }
            }

        }
        return instance;
    }
}
