package me.whiteship.java8to11.interfacechange;

/**
 * Foo, Foo2를 동시 구현
 * default 메서드가 충돌나게 됨
 * 이렇게 충돌나는 경우, 직접 오버라이딩을 해야한다.
 */
public class MultiFoo implements Foo, Foo2 {

    @Override
    public void printName() {

    }

    @Override
    public void printNameUpperCase() {
        Foo.super.printNameUpperCase();
    }

    @Override
    public String getName() {
        return null;
    }
}
