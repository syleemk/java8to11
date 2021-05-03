package me.whiteship.java8to11.interfacechange;

public interface Foo2 {

    /**
     * Foo의 기본 메서드랑 충돌
     */
    default void printNameUpperCase() {
        System.out.println("BAR");
    }
}
