package me.whiteship.java8to11.interfacechange;

/**
 * Foo 인터페이스를 상속받는 Bar 인터페이스
 * Bar에서는 Foo 에서 제공하는 default method 구현체인 printNameUpperCase를 제공하고 싶지 않음
 * 그런 경우에는, Bar에서 다시 추상 메서드로 선언해주면 된다.
 */
public interface Bar extends Foo{

    /**
     * 추상메서드로 선언 안하면
     * Foo 에서 제공된 기본 구현체가 제공된다.
     * 하지만 이렇게 추상메서드 선언하면
     * Bar를 구현하는 모든 인스턴스는 해당 메서드를 다 직접 구현해야한다.
     */
    void printNameUpperCase();
}
