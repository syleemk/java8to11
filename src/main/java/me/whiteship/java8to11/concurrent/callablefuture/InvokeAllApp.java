package me.whiteship.java8to11.concurrent.callablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> sooyoung = () -> {
            Thread.sleep(1000L);
            return "Sooyoung";
        };

        /**
         * 여러 개의 작업을 한번에 리스트 형태로 전달가능
         * invokeAll() API 사용
         * 리턴값은 Future의 리스트로 받음
         */
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, sooyoung));
        for (Future<String> f : futures) {
            System.out.println("f.get() = " + f.get());
        }        

        executorService.shutdown();
    }
}
