package me.whiteship.java8to11.functionalinterface;

/**
 * 딱 하나의 "추상 메서드"만을 가지는 인터페이스
 * = 함수형 인터페이스
 * 
 * Java8에서는 interface 안에서 정의할 수 있는 메서드 형태가 좀 더 다양해짐
 *
 * @FunctionalInterface 애노테이션을 통해 컴파일시에 오류 잡아낼 수 있다
 * (마치 @Override 애노테이션처럼)
 */
@FunctionalInterface
public interface RunSomething {

    // 인터페이스에서는 abstract 키워드 생략 가능
    void doIt();

    // Java8의 새 기능
    // 인터페이스에 static 메서드 정의 가능 - (public 키워드 생략해도 public)
    static void printName() {
        System.out.println("RunSomething");
    }

    // default 메서드 정의 가능
    default void printAge() {
        System.out.println("27");
    }
}
