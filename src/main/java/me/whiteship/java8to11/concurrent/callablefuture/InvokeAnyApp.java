package me.whiteship.java8to11.concurrent.callablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAnyApp {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

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
         * blocking call 이고 바로 Future도 아니고 해당 타입으로 결과가 나온다.
         * 참고로 싱글 스레드의 경우 블로킹 연산처럼 결과가 나온다.
         * (왜냐하면 스레드 하나니까 먼저 들어간 작업이 마무리 되야 다음 작업 실행될 수 있음)
         * 따라서 스레드를 작업수보다 많게 해주자.
         */
        String s = executorService.invokeAny(Arrays.asList(hello, java, sooyoung));
        System.out.println("s = " + s);

        executorService.shutdown();
    }
}
