package io.haitaoc.concurrency.example.singleton;

import io.haitaoc.concurrency.annotation.NotThreadSafe;

/**
 * 懒汉模式
 * 单例的实例在第一次使用的时候进行创建
 */
@NotThreadSafe
public class SingletonExample {

    // 首先要保证这个类不能随便被new一个新的对象出来，所以将构造函数私有化
    private SingletonExample(){

    }

    // 定义一个单例对象，每次都是只返回这同一个对象
    private static SingletonExample instance = null;

    // 静态的工厂方法获取一个单例对象
    public static SingletonExample getInstance(){
        if(instance==null){
            instance = new SingletonExample();
        }
        return instance;
    }
}
