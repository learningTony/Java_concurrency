package io.haitaoc.concurrency.example.immutable;

import io.haitaoc.concurrency.annotation.NotThreadSafe;
import io.haitaoc.concurrency.annotation.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class ImmutableExample2 {


    private  static Map<Integer,Integer> map = new HashMap<>();

    static{
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
      //  a=2;      // 数值不能改变
      //  b="3";
      // map = new HashMap<>()    // 不能指向新的引用
        map.put(1,3);       // 里面的数值对可以改变
        map.entrySet().forEach(entry -> System.out.println("key:value = " + entry.getKey() + ":" + entry.getValue()));
    }



}
