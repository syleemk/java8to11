package me.whiteship.java8to11.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 컨테이너 애노테이션의 범위는
 * 그 안에 포함되는 애노테이션의 범위랑 같거나 더 넓어야함
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface ChickenContainer {

    /**
     * 이 안에 자기 자신이 가지고 있을 애노테이션을 배열로 가지고 있으면 된다.
     */
    ChickenRepeatable[] value();
}
