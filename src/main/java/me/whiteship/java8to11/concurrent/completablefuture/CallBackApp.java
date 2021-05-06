package me.whiteship.java8to11.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 쓰레드 작업 완료되었을 때 실행되는 콜백을 전달할 수 있다.
 * 하지만 여전히 get으로 결과값은 가져와야한다.
 */
public class CallBackApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 리턴값을 받아서 다른 값으로 변환하는 콜백
         */
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply(s -> {
            System.out.println(s.toUpperCase());
            return s.toUpperCase();
        });

        /**
         * 여전히 get()을 통해 future로 부터 결과값을 가져와야한다.
         * 다만 Java5에서 future만 가지고 했을 때아ㅗ 차이점은,
         * callback을 get 호출하기 전에 정의할 수 있다는 것!!
         * 이전에는 무조건 get으로 결과값을 가져온 후, 그 후에 처리해야했다.
         */
        System.out.println("future.get() = " + future.get());

        /**
         * 리턴값이 없는 경우의 콜백
         */
        CompletableFuture<Void> noReturnFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept(s -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });

        // 리턴은 없다.
        System.out.println("noReturnFuture.get() = " + noReturnFuture.get());

        /**
         * 실행결과값을 인자로 받을 필요도 없고,
         * 작업 완료후 특정 작업을 하기만 하면 된다.
         */
        CompletableFuture<Void> runFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        runFuture.get();
    }
}
