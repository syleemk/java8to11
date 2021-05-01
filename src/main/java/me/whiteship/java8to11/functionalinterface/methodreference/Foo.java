package me.whiteship.java8to11.functionalinterface.methodreference;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Foo {

    public static void main(String[] args) {

        // 아래 3개는 다 같은 기능
        // 람다표현식에서 단순히 메소드를 호출하는 작업만 한다면
        // 메소드 참조로 대체할 수 있다.
        // static 메소드 레퍼런스
        // 타입::스태틱 메소드
        UnaryOperator<String> hi = s -> "hi" + s;
        UnaryOperator<String> hi2 = s -> Greeting.hi(s);
        UnaryOperator<String> hi3 = Greeting::hi; // static 메소드 참조

        // 인스턴스 메소드 참조
        // 특정 객체의 인스턴스 레퍼런스::인스턴스 메소드
        Greeting greeting = new Greeting();
        UnaryOperator<String> gr = greeting::hello;

        // 생성자 참조
        // 타입::new
        // 입력값은 없고 리턴값만 있는 함수형 인터페이스 = Supplier<가져올 타입 명시>
        // 이 자체로는 아무것도 아님
        // 단순히 해당 메서드를 참조하고있는 supplier일 뿐임
        // 실행시키려면 해당 인터페이스의 메서드를 호출해줘야함
        Supplier<Greeting> newGreeting = Greeting::new; 

        // supplier 인터페이스의 추상메서드인 get을 호출해줘야
        // get에서 내부적으로 Greeting 기본 생성자가 호출됨
        // 생성된 객체 리턴
        Greeting g = newGreeting.get();

        // 입력값을 받는 생성자
        // 입력값과 리턴값 둘다 있으므로 Function 인터페이스 사용
        // 위에 나온 생성자 레퍼런스와 같아보이지만, 실제로는 서로 다른 생성자를 참조하는것
        // 위는 기본생성자, 이거는 name을 파라미터로 받는 생성자
        Function<String, Greeting> newNameGreeting = Greeting::new;

        Greeting sooyoung = newNameGreeting.apply("sooyoung");
        System.out.println(sooyoung.getName());
        
        // 임의의 객체의 인스턴스 메소드 참조하는 방법
        // 임의의 객체들을 차례로 참조할 때 해당 객체들의 인스턴스 메소드들을 참조하는 방법
        String[] names = {"keesun", "whiteship", "toby"};
        // 람다 표현식이 올 수 있다는 것은 메소드 레퍼런스도 올 수 있다는 뜻
        // 왜냐하면 람다표현식이 단순 함수 호출만 하는 경우 메소드 레퍼런스로 대체할 수 있는 것이니까
        Arrays.sort(names, (o1, o2) -> 0);
        
        // compareToIgnoreCase는 인스턴스 자신의 값과 인자로 전달된 인스턴스의 값을 비교
        // 임의의 인스턴스들을 거쳐가면서 (keesun -> whiteship -> toby 라는 String 인스턴스 차례로 거쳐감)
        // String 클래스의 compareToIgnoreCase 인스턴스 메소드를 사용함
        Arrays.sort(names, String::compareToIgnoreCase);

        // 사실은 이것과 같은 표현
        // 해당 람다식에 전달된 2개의 인자중 첫번째 인자의 인스턴스 메소드를 호출하기로 약속된것!!!
        Arrays.sort(names, (s1, s2) -> s1.compareToIgnoreCase(s2));
    }
}
