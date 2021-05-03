package me.whiteship.java8to11.interfacechange;

public interface Foo {

    void printName();

    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    /**
     * static 으로,
     * 해당 인터페이스 타입 관련된 헬퍼 메서드 혹은 유틸메서드 제공
     * default는 인스턴스에게 제공하는 메서드
     */
    static void printAnything() {
        System.out.println("anything");
    }

    String getName();
}
