package me.whiteship.java8to11.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorsApp {

    public static void main(String[] args) {
        /**
         * Executors에서 제공하는 static factory method사용해서 생성
         * newSingleThreadExecutor는 스레드를 하나만 사용하는 executors
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /**
         * submit하면 실행이된다.
         * 주의할 점은 executorService는 스레드 만들어서 작업 실행하고나면,
         * 다음 작업이 들어올 때까지 계속해서 대기하기 때문에 프로세스가 죽지 않는다.
         * 명시적으로 shutdown 해야한다.
         */
        executorService.submit(()->{
            System.out.println("Thread: " + Thread.currentThread().getName());
        });

        /**
         * 명시적인 shutdown
         * shutdown은 graceful shutdown이다.
         * 현재 진행중인 작업은 끝까지 마치고 끝낸다.
         * 그런것 상관없이 바로 죽이고싶으면 shutdownNow 사용
         */
        executorService.shutdown();
    }
}
