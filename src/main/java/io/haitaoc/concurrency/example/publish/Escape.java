package io.haitaoc.concurrency.example.publish;


import io.haitaoc.concurrency.annotation.NotThreadSafe;

@NotThreadSafe
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    /**
     * 内部类的实例里面包含了对封装内容thisCanBeEscape的隐含引用
     * 这样在对象没有被正确构造之前，他就会被发布，有可能有不安全的因素在
     * 一个导致this引用在构造期间逸出的错误   是在构造的函数过程中启动了一个线程
     * 无论是隐式的启动还是显示地启动都会造成this引用的逸出，新线程总是会在对象构造完毕
     * 之前就已经看到this引用    所以要再构造函数中使用线程，就不要启动它而应该专有的start或初始化的方法来统一启动线程，
     * 可以采用工厂方法和私有构造函数来完成对象创建和监听器的注册等
     *
     *
     * 在对象未完成构造之前   不可以将其发布
     */
    private class InnerClass{

        public InnerClass(){
            System.out.println(Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }


}
