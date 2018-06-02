package io.haitaoc.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)   // 注解作用的地方
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {
    String value() default "";
}
