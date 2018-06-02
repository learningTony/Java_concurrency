package io.haitaoc.concurrency.example.singleton;


import io.haitaoc.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式
 * 单例的实例在类装载的时候进行创建
 *
 * 缺点：如果构造函数中有过多的操作处理，会导致类加载的时候特别慢，可能会引起性能问题
 *  如果使用饿汉模式只进行类加载而没有实际调用   会造成资源的浪费
 *
 */
@ThreadSafe
public class SingletonExample2 {

    // 首先要保证这个类不能随便被new一个新的对象出来，所以将构造函数私有化
    private SingletonExample2(){

    }

    // 定义一个单例对象，每次都是只返回这同一个对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法获取一个单例对象
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
