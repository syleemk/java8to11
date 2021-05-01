package me.whiteship.java8to11.functionalinterface.basicinterface;

import java.util.function.Function;

// 미리 정의되어있는 자바에서 제공하는 Function 함수형 인터페이스 구현
public class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
