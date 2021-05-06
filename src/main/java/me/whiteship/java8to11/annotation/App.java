package me.whiteship.java8to11.annotation;

import java.util.Arrays;
import java.util.List;

/**
 * 애노테이션 중복 가능
 */
@ChickenRepeatable("BBQ")
@ChickenRepeatable("BHC")
@ChickenTypeUse
public class App {

    public static void main(String[] args) throws @ChickenTypeUse RuntimeException {
        List<@ChickenTypeUse String> names = Arrays.asList("sooyoung");

        // 애노테이션을 읽어다가 쓸 수도 있음
        ChickenRepeatable[] chickens = App.class.getAnnotationsByType(ChickenRepeatable.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });

        // 해당 애노테이션의 컨테이너 타입으로 가져올 수 있음
        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c->{
            System.out.println(c.value());
        });
    }

    /**
     * 제네릭 타입 파라미터에 붙일 수 있다 - ElementType.TYPE_PARAMETER
     */
    static class FeelsLikeChicken<@ChickenTypeParameter T> {

        /**
         * TYPE_USE는 TYPE_PARAMETER보다 좀 더 제네럴하게 사용할 수 있다.
         * TYPE을 사용하는 모든 곳에 붙일 수 있다.
         * 타입 파라미터도 포함하는 것임 (타입 사용하니까)
         */
        public static <@ChickenTypeParameter C> void print(@ChickenTypeUse C c) {

        }
    }
}
