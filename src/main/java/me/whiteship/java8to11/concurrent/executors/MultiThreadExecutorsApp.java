package me.whiteship.java8to11.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadExecutorsApp {

    public static void main(String[] args) {

        /**
         * 스레드풀에 스레드는 2개, 작업은 5개
         * 실행은 되지만, 2개의 스레드를 가지고 번갈아가면서 실행
         */
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("Hello "));
        executorService.submit(getRunnable("Sooyoung "));
        executorService.submit(getRunnable("The "));
        executorService.submit(getRunnable("Java "));
        executorService.submit(getRunnable("Thread "));

        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
