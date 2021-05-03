package me.whiteship.java8to11.interfacechange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class App {

    public static void main(String[] args) {
        Foo foo = new DefaultFoo("sooyoung");
        foo.printName();
        foo.printNameUpperCase();

        List<String> name = new ArrayList<>();
        name.add("keesun");
        name.add("whiteship");
        name.add("toby");
        name.add("foo");

        /**
         * Java 8에 추가된 기본 메서드들
         */
        // Iterable 인터페이스의 forEach()
        // 아래 것은 메서드 레퍼런스중 특정 객체의 인스턴스 메서드 참조
        // System.out : 객체 레퍼런스 (참조변수) , println : 인스턴스 메소드
        name.forEach(System.out::println);

        // Iterable 인터페이스의 spliterator()
        // 쪼개서 순회가 가능
        // 기본적으로 iterator랑 같아서 순회하는데 사용
        Spliterator<String> spliterator = name.spliterator();
        // trySplit을 통해 쪼갤 수 있다.
        // parallel하게 처리할 때 유용하게 쓰일 수 있음
        Spliterator<String> spliterator2 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("=============");
        while(spliterator2.tryAdvance(System.out::println));

        // Collection 인터페이스의 removeIf(Predicate) 디폴트 메소드
        name.removeIf(s -> s.startsWith("k"));

        name.forEach(System.out::println);

        // Comparator : Functional Interface
        // 구현해야할 추상메서드 하나, 나머지 다 기본 메소드 혹은 static 메소드
        // 구현할 추상메서드를 표현하는 람다식에서 단순 메소드 하나 호출할 때 메소드 레퍼런스로 변경 가능
        // String::compareToIgnoreCase, 임의 객체의 인스턴스 메소드 참조
        // 해당 인스턴스 타입 String :: 인스턴스 메소드 compareToIgnoreCase
        // 두개의 인자가 전달되면 첫번째 인자의 인스턴스 메소드를 호출하는 것임
        //  (a,b) -> 일때 a.compareToIgnoreCase(b) 하는 것
        Comparator<String> comparator = String::compareToIgnoreCase;
        // 역순으로 정렬하고 싶으면 reversed default 메소드 호출
        name.sort(comparator.reversed());
    }
}
