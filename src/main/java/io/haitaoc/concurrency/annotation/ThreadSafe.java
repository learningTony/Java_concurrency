package io.haitaoc.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 课程里用来标记线程安全的类或者写法
 */
@Target(ElementType.TYPE)   // 注解作用的地方
@Retention(RetentionPolicy.SOURCE)        //注解存在的范围, 起到标志作用
public @interface ThreadSafe {

    String value() default "";
}
