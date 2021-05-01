package me.whiteship.java8to11.functionalinterface.lambdaexpression;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Foo {

    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.run();
    }

    // 변수 캡쳐
    // 셰도잉
    private void run() {
        int baseNumber = 10;
        
        // 메소드 내부에 선언한 지역 클래스 (로컬 클래스)
        // 내부에 별도 스코프를 가진다.
        class LocalClass {
            void printBaseNumber() {
                // 쉐도잉, 변수 재정의
                int baseNumber = 11;

                // 외부 변수 참조 : 변수 캡쳐 가능
                // 쉐도잉 일어남
                System.out.println("baseNumber = " + baseNumber); // 11
            }
        }

        // 익명 클래스
        // 내부에 별도 스코프를 가진다.
        Consumer<Integer> anonymousClass = new Consumer<>() {
            @Override
            public void accept(Integer baseNumber) {
                // 외부 변수 참조 : 변수 캡쳐 가능
                // 쉐도잉 기능 : 더이상 익명클래스를 감싸고있는 외부 클래스의 baseNumber 참조하지 않음
                // 익명클래스의 내부 스코프에 정의된 파라미터를 가리킴
                System.out.println(baseNumber);
            }
        };

        // 람다 표현식
        // 따로 내부 스코프를 가지지 않는다.
        // 람다를 감싸는 스코프가 람다의 스코프이다.
        // 람다 바디 내부의 스코프는 람다 외부의 스코프와 "같다"
        // 같은 스코프 안에 똑같은 변수 "재정의" 불가능!!
        IntConsumer printInt = i -> {
            // 외부 변수를 참조
            // 참조되고 있는 local variable이 캡쳐가 된다. 
            System.out.println(i + baseNumber);
        };
    }


}
