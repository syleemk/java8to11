package me.whiteship.java8to11.annotation;

import java.lang.annotation.*;

/**
 * Retention : 애노테이션 정보를 언제까지 유지할 것인가
 * Target : 애노테이션을 사용할 곳 - 자바 8에서 2가지 타입이 추가가됨
 * - TYPE_USE
 * - TYPE_PARAMETER
 * Repeatable : 해당 애노테이션이 담길 컨테이너 애노테이션 명시
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_PARAMETER)
public @interface ChickenTypeParameter {


}
