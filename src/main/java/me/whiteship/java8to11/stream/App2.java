package me.whiteship.java8to11.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App2 {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(c -> c.getTitle().startsWith("spring"))
                .forEach(c -> System.out.println(c.getId()));

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(oc -> !oc.isClosed())
                .forEach(oc -> System.out.println(oc.getId()));

        //위 람다식을 메소드 레퍼런스와 Predicate.not() 메소드를 통해 개선
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        // 리스트를 담고있는 리스트
        List<List<OnlineClass>> sooyoungEvents = new ArrayList<>();
        sooyoungEvents.add(springClasses);
        sooyoungEvents.add(javaClasses);

        /**
         * Stream의 map : 일대일 매핑, element하나가 오면 result도 딱 하나.
         * Stream의 flatMap : mapping해줄 객체가 컨테이너 성격의 객체일 경우, 그 안의 데이터들 꺼내서 매핑할 때 사용
         * input은 하나지만, output이 여러 개인 경우 사용한다.
         */
        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // 스트림에 들어있는 것이 그냥 객체가 아닌, 컬렉션 타입인 경우
        // 컬렉션 타입안에 또 여러 개의 인스턴스들이 존재
        // 이를 flatten(컬렉션 안의 데이터를 스트림으로 flat하게 만들어줌) 시켜줘야함 -> flatMap
        sooyoungEvents.stream()
                .flatMap(list -> list.stream()) //리스트 타입을 stream으로 flatten 해주기
                .forEach(oc -> System.out.println(oc.getId()));

        //메소드 레퍼런스로 바꾼 방식
        sooyoungEvents.stream()
                .flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getId()));


        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1) // 이렇게만 하면 무제한 스트림
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream()
                .anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());

        spring.forEach(System.out::println);
    }
}
