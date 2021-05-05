package me.whiteship.java8to11.concurrent.callablefuture;

import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        /**
         * Runnable과 다르게 Callable은 리턴값을 가진다.
         */
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        /**
         * Callable의 리턴값을 Future로 받는다.
         * Future에서 결과값을 get으로 꺼낼 수 있다.
         * get() 이전까지는 논블로킹으로 코드가 실행된다.
         * get()을 만난순간 코드가 블로킹되고 결과값이 리턴되기를 기다린다.
         */
        Future<String> helloFuture = executorService.submit(hello);
        System.out.println("helloFuture.isDone() = " + helloFuture.isDone());
        System.out.println("Started!");

        // 블로킹 콜
        //helloFuture.get();

        // 작업 취소
        helloFuture.cancel(false);

        System.out.println("helloFuture.isDone() = " + helloFuture.isDone());
        System.out.println("End!! ");

        executorService.shutdown();
    }
}
