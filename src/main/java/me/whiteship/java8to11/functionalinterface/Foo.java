package me.whiteship.java8to11.functionalinterface;

public class Foo {

    public static void main(String[] args) {
        // 익명 내부 클래스 anonymous inner class
        RunSomething runSomething1 = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        // 함수형 인터페이스 (구현해야할 추상메서드가 하나인 인터페이스)
        // Java8부터는 람다 표현식을 익명 내부 클래스 대신 사용 가능
        // 람다표현식을 사용하면 다른언어에서 제공하는 것처럼 함수를 정의한 것 처럼 보이지만
        // 실제로는 함수형 인터페이스라는 특별한 "객체"를 정의한 것임
        // "객체"기 때문에 변수에 할당하고, 메서드 파라미터로 전달하고, 리턴해줄 수 있다
        RunSomething runSomething2 = () -> System.out.println("Hello");

        runSomething1.doIt();
        runSomething2.doIt();

        // 순수함수는 수학적인 개념
        // 수학적인 개념에서 가장 중요한 것은 입력받은 값이 같으면 결과도 같아야함
        PureFunction pureFunction = (number) -> {
            return number + 10;
        };

        // 1을 넣었으면 동일한 11이 계속 출력되어야한다.
        // 이거를 보장해주지 못할 상황, 혹은 여지가 있다면 함수형 프로그래밍이라 보기 어렵다.
        System.out.println(pureFunction.doIt(1));
        System.out.println(pureFunction.doIt(1));
        System.out.println(pureFunction.doIt(1));

        // 동일함을 보장해주지 못하는 상황은 함수 안에서 함수 바깥의 값을 참조해서 쓰는 경우
        int baseNumber = 10;

        // 이렇게 함수 안에서 함수 바깥의 값을 가지고 있는 경우 pure 하다고 볼 수 없다.
        // 이런 경우는 상태값을 가지고 있다, 상태값에 의존한다고 말한다.
        PureFunction nonPureFunction = (number)->{
            return number + baseNumber;
        };

        System.out.println(nonPureFunction.doIt(1));
        System.out.println(nonPureFunction.doIt(1));
        System.out.println(nonPureFunction.doIt(1));

    }
}
