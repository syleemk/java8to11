package me.whiteship.java8to11.optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

//        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);

        /**
         * 이 코드가 무사히 실행될까? -> null pointer exception 발생
         * 따라서 null check를 해줘야함
         */
//        Duration studyDuration = spring_boot.getProgress().getStudyDuration();
//        System.out.println(studyDuration);


        /**
         * 그래서 이런식으로 null check해왔음 
         * 그러나 이는 매우 에러를 발생시키기 쉬운 코드임 -> null check를 깜빡할 수 있기 때문에
         * 문제점1 : null check를 깜빡할 수 있다. (사람이기 때문에)
         * 문제점2 : null을 리턴하는 것 자체가 문제다.
         */
//        Progress progress = spring_boot.getProgress();
//        if (progress != null) {
//            System.out.println(progress.getStudyDuration());
//        }

        /**
         * Stream API를 사용할 때도 Optional을 리턴하는 오퍼레이션들이 몇몇 있다.
         * Optional을 리턴한다는 것은 중개 연산이 아닌 종료연산이라는 것
         * (중개연산은 Stream을 리턴 -> 체이닝 가능)
         */
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        /**
         * 가능하면 Optional.get() API 말고 Optional이 제공하는 다른 API들을 사용하는 것 권장
         */
        if (optional.isPresent()) {
            // 만약 optional이 비어있는데 거기로부터 무언가 꺼내려고하면(get)
            // Runtime Exception 발생
            OnlineClass onlineClass = optional.get();
            System.out.println(onlineClass.getTitle());
        }

        /**
         * Optional이 비어있지 않으면, Optional안의 값 가지고 할 일 구현해주면 됨
         */
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        /**
         * Optional안의 값을 무조건 꺼내야할 때 (그 값 가지고 뭐하는게 아니라)
         * 해당 API 메서드 인자로 Functional Interface의 구현이 들어가는 것이 아니라
         * Optional이 감싸고 있는 타입의 인스턴스가 들어간다.
         *
         * optional이 비어있든 비어있지 않든 orElse의 인자로 전달된
         * 인스턴스를 생성하는 연산은 "무조건 실행"된다.
         */
        OnlineClass onlineClass = optional.orElse(createNewClass());
        System.out.println(onlineClass.getTitle());

        /**
         * 인스턴스 생성 연산을 무조건 하지 않는 것은 orElseGet
         * Optional이 비어있는 경우 supplier을 실행하지 않음
         */
        OnlineClass onlineClass2 = optional.orElseGet(App::createNewClass);
        System.out.println(onlineClass2.getTitle());

        /**
         * Optional이 비어있는 경우 새로운 인스턴스를 생성해서 반환하는게 아니라
         * 에러를 발생시킴
         */
        OnlineClass onlineClass3 = optional.orElseThrow();
        System.out.println(onlineClass3.getTitle());

        /**
         * optional에 filter 걸 수도 있다.
         * 조건 만족하는 값은 optional에 감싸진채로 나온다.
         */
        Optional<OnlineClass> onlineClass4 = optional.filter(oc -> !oc.isClosed());
        System.out.println(onlineClass4.isEmpty());

        /**
         * map 사용가능
         * 복잡해질 수 있는게, map으로 변환하려는 타입이 Optional인 경우
         * (사실 OnlineClass 객체에서 getProgress는 Optional 타입을 반환함)
         */
        Optional<Integer> optionalId = optional.map(OnlineClass::getId);
        System.out.println(optionalId.isPresent());

        /**
         * 굉장히 복잡해지기 시작한다. Optional안에 Optional 있음
         * 양파껍질까듯이 벗겨내야함
         * -> 이런경우 유용하게 사용할 수 있는 메서드가 바로 flatMap 이다.
         */
        Optional<Optional<Progress>> progress = optional.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElseThrow();
        progress1.orElseThrow();

        /**
         * Optional이 제공하는 flatMap은 mapping해서 꺼내는 타입 자체가 optional인 경우
         * 그 껍질을 한번 까준다.
         */
        Optional<Progress> progress2 = optional.flatMap(OnlineClass::getProgress);

    }

    private static OnlineClass createNewClass() {
        System.out.println("create new online class");
        return new OnlineClass(10, "New class", false);
    }
}
