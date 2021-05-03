package me.whiteship.java8to11.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("sooyoung");
        names.add("hyunzoon");
        names.add("booyong");

        /**
         * 그냥 스트림
         */
        List<String> collect = names.stream().map(s -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);

        System.out.println("=======================");

        /**
         * 병렬스트림
         * -> 병렬처리가 항상 성능이 좋은 것이 아님!
         * (항상 성능 좋다면 요새 등장하는 리액티브 프로그래밍이 대두될 일이 없음)
         */
        List<String> collectParallel = names.parallelStream().map(s -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collectParallel.forEach(System.out::println);
    }
}
