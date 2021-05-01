package me.whiteship.java8to11.functionalinterface.basicinterface;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {
        // 인스턴스를 생성해서 쓰는 방법
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(10));

        // 람다식으로 바로 구현해서 쓸 수도 있음
        // 구현된 것은 함수처럼 보여도 특별한 객체임
        // 따라서 참조 변수로 받을 수 있다.
        Function<Integer, Integer> plus10Lambda = number -> number + 10;
        System.out.println(plus10Lambda.apply(10));

        // Function 인터페이스는 함수 조합용 메서드를 제공
        Function<Integer, Integer> plus20 = number -> number + 20;
        Function<Integer, Integer> multiply2 = number -> number * 2;
        
        // 위의 두 함수(객체) 조합 가능
        // compose의 경우 입력값을 가지고 먼저 안에 오는 함수를 적용한 후
        // 그 결과값을 다시 바깥 함수의 입력값으로 사용
        // 고차함수의 성격
        Function<Integer, Integer> multiply2AndPlus20 = plus20.compose(multiply2);
        System.out.println(multiply2AndPlus20.apply(2));

        // andThen은 compose와 달리 뒤에다가 함수를 붙임
        // compose는 앞에다가 붙여서 해당 함수의 결과값을 입력값으로 받았음
        // andThen은 자신의 결과를 다음 함수의 입력값으로 넘겨줌
        Function<Integer, Integer> plus20AndMultiply2 = plus20.andThen(multiply2);
        System.out.println(plus20AndMultiply2.apply(2));

        // Cosumer
        // 리턴타입없이 입력값 받아서 해당 입력값으로 처리만 하는 것
        Consumer<Integer> printT = i -> System.out.println(i);
        printT.accept(10);

        // Supplier
        // 내가 이 함수로부터 받아올 값을 정의함
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // Predicate
        Predicate<Integer> isOdd = i -> i % 2 == 1;

        // UnaryOperator
        // 앞에서 봤던 Function 함수 인터페이스의 특수한 케이스
        Function<Integer, Integer> plus30 = i -> i + 30;

        // 위와 같이 입력값의 타입과 리턴 타입이 같은 경우 UnaryOperator 사용가능
        UnaryOperator<Integer> plus30Unary = i -> i + 30;

        // BinaryOperator
        // BiFunction의 특수한 형태
        // BiFuncion<T,U,R>의 경우 T와 U, R의 타입이 모두 다를 거라는 가정에서 나온 것
        // BinaryOperator는 세 개의 타입이 모두 같은 경우 사용 (굳이 타입을 3번 적지 않아도됨)
        BinaryOperator<Integer> plusTwoNumber = (i, j) -> i + j;

    }
}
