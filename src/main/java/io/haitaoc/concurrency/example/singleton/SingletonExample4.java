package io.haitaoc.concurrency.example.singleton;

import io.haitaoc.concurrency.annotation.NotRecommend;
import io.haitaoc.concurrency.annotation.NotThreadSafe;
import io.haitaoc.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式 》 双重同步锁单例模式
 * 首先将synchronized放到方法内部去
 */
@NotThreadSafe
@NotRecommend
public class SingletonExample4 {

    // 首先要保证这个类不能随便被new一个新的对象出来，所以将构造函数私有化
    private SingletonExample4(){

    }

    // 定义一个单例对象，每次都是只返回这同一个对象
    private static SingletonExample4 instance = null;

    // 1. memeory = allocate()  分配对象的内存空间
    // 2. contructInstance()   初始化对象
    // 3. instance=memory 设置instance指向刚分配的内存

    // JVM和cpu优化，发生了指令重排：
    // 1. memeory = allocate()  分配对象的内存空间
    // 3. instance=memory 设置instance指向刚分配的内存
    // 2. contructInstance()   初始化对象

    // 线程A 经过了第1步直接进行第3步, 线程B发现instance不为null
    // 就直接return instance  但instance没有初始化就返回了  就会出现问题

    // 静态的工厂方法获取一个单例对象
    public static  SingletonExample4 getInstance(){
        if(instance==null){         // 双重检测机制       // B
            synchronized (SingletonExample4.class){     // 同步锁
                if(instance==null){
                    instance = new SingletonExample4();     // A - 3
                }
            }

        }
        return instance;
    }
}
