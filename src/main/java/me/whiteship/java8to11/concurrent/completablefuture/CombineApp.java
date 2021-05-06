package me.whiteship.java8to11.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CombineApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        /**
         * 조합할 두 작업 사이의 연관관계가 없어서 따로 실행하는 경우
         * 둘이 따로따로 실행하고, 둘 다 결과가 왔을 때 무언가 하고싶은 경우
         * Combine을 사용하면 됨
         * -> 입력 값은 2개 결과값은 하나 -> BiFunction을 사용
         */
        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> {
            return h + " " + w;
        });
        System.out.println(future.get());

    }
}
