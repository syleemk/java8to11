package me.whiteship.java8to11.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ComposeApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        //CompletableFuture<String> world = getWorld();

        /**
         * Hello 끝난 후에 Wolrd를 해야한다.
         * 이전에는 콜백을 주거나, 무언가 연결하는 작업이 없어서
         * get으로 blocking으로 기다려야했음
         * 
         * hello를 먼저 get으로 기다린 후 이어서 world를 get으로 기다림
         * 사실 제대로 된 코드는 world completablefuture위에 hello.get()이 있어야함
         */
        //System.out.println(hello.get());
        //System.out.println(world.get());

        /**
         * 두개의 스레드 작업을 연결한 future가 나온다.
         * thenCompose API는 future간에 의존성이 있는 경우 사용
         * 뭐 먼저 해야하고, 그다음에 이거 해야할 때
         */
        CompletableFuture<String> future = hello.thenCompose(ComposeApp::getWorld);
        System.out.println(future.get());

    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
