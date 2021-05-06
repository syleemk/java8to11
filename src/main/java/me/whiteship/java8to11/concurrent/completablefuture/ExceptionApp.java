package me.whiteship.java8to11.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExceptionApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        boolean throwError = true;

        /**
         * 에러 발생시 처리 명시 exceptionally
         */
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error";
        });
        System.out.println(hello.get());

        /**
         * exceptionally보다 좀 더 general한 경우 handle
         * 정상적인 경우, 에러 발생경우 모두 사용 가능
         */
        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        }).handle((result, err)-> {
            if (err != null) {
                System.out.println(err);
                return "Error";
            }

            return result;
        });
        System.out.println(world.get());

    }
}
