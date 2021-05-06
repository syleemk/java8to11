package me.whiteship.java8to11.concurrent.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future = executorService.submit(() -> "hello");

        /**
         * get()이 블로킹 콜이기 때문에, get() 전에 최대한 할 수 있는 것 많이 해야한다.
         * 하지만, 실제로는 위에서 실행되는 hello 작업이 완료되어야 할 수 있는 작업들도 여기에 같이 많이 써줌
         * 그것이 일반적인 비동기 프로그래밍을 지원하는 언어에서 코딩하는 패턴임
         * 하지만 자바5에서 제공하는 Future만으로 그렇게 하기는 굉장히 어려웠음
         */
        future.get();
        /**
         * Future에서 값을 받아서 무언가를 하는 작업은 get 이후에 와야한다.
         */
    }
}
