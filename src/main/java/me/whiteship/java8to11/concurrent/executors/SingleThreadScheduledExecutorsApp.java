package me.whiteship.java8to11.concurrent.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SingleThreadScheduledExecutorsApp {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // delay 줄 수 있음
        executorService.schedule(getRunnable("Hello "), 3, TimeUnit.SECONDS);
        // delay와 반복 회수 줄 수 있음
        // shutdown해주면 안에있는 task가 인터럽트 받아서 종료되기 때문에 shutdown 하지말고 실행해야함
        executorService.scheduleAtFixedRate(getRunnable("World "), 1, 2, TimeUnit.SECONDS);

        //executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
