package io.haitaoc.concurrency.example.immutable;

import io.haitaoc.concurrency.annotation.NotThreadSafe;

import java.util.HashMap;
import java.util.Map;

@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a=1;
    private final static String b="2";
    private final static Map<Integer,Integer> map = new HashMap<>();

    static{
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
      //  a=2;      // 数值不能改变
      //  b="3";
      // map = new HashMap<>()    // 不能指向新的引用
        map.put(1,3);       // 里面的数值对可以改变
        map.entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
    }

    private void test(final int a){
        // a = 1;          //  不允许修改传参为final修饰的变量
    }

}
