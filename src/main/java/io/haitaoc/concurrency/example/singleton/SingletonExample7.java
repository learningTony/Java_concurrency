package io.haitaoc.concurrency.example.singleton;

import io.haitaoc.concurrency.annotation.Recommend;
import io.haitaoc.concurrency.annotation.ThreadSafe;

/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只被调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }

    public static void main(String[] args) {
        SingletonExample7 obj1 = Singleton.INSTANCE.getInstance();
        SingletonExample7 obj2 = Singleton.INSTANCE.getInstance();
        //输出结果：obj1==obj2?true
        System.out.println("obj1==obj2?" + (obj1==obj2));
    }
}
