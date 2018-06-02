package io.haitaoc.concurrency.example.singleton;

import io.haitaoc.concurrency.annotation.NotRecommend;
import io.haitaoc.concurrency.annotation.NotThreadSafe;
import io.haitaoc.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式
 * 获取实例的静态工厂方法在添加了synchronized修饰以后
 * 方法内的所有实现，在同一时间内只允许一个线程访问
 * 因此可以保证是线程安全的
 * 但是带来了性能上的开销
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 首先要保证这个类不能随便被new一个新的对象出来，所以将构造函数私有化
    private SingletonExample3(){

    }

    // 定义一个单例对象，每次都是只返回这同一个对象
    private static SingletonExample3 instance = null;

    // 静态的工厂方法获取一个单例对象
    public static synchronized SingletonExample3 getInstance(){
        if(instance==null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
