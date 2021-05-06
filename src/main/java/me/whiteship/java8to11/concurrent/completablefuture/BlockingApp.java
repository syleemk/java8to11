package me.whiteship.java8to11.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Complete이라는 게 붙은 이유는 외부에서 Complete을 시킬 수 있기 때문
 * 가령 몇초이내에 응답이 안오면, 기본값으로 미리 정해둔 값을 리턴하도록 만들 수도 있고,
 * 또 CompletableFuture를 쓰면은 더이상 명시적으로 executor 만들어서 사용할 필요 없다.
 * CompletableFuture만 가지고 비동기적인 작업들을 실행할 수 있다.
 */
public class BlockingApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        //future의 기본값을 정해줌
        future.complete("sooyoung");

        System.out.println("future.get() = " + future.get());

        CompletableFuture<String> future2 = CompletableFuture.completedFuture("sooyoung");

        /**
         * 실제로 단순히 기본값을 리턴하는게 아닌 작업을 하고싶다면
         * 리턴이 없는 경우 - runAsync()
         * 리턴 있는 경우 - supplyAsync()
         */
        // 단순히 Future만 정의한 것이기 때문에 작업이 일어나지 않음
        // 사실 작업이 일어남, 오해할만하게 말하심
        CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        // get또는 join을 해야 작업이 발생함
        // join을 하면 안에서 어떤 Exception이 발생하는 상황에서 uncatched exception으로 던져준다.
        // 굳이 에러처리를 명시안해줘도 되니까 더 편한 경우도 있을 수 있다.
        runFuture.get();

        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });
        //위에거는 단순 정의 -> ㄴㄴ 작업일어남
        //작업 일어남, 다만 main 스레드 입장에서 get이나 join을 통해 해당 스레드 완료를 기다리지 않는다면
        //해당 작업이 완료되지 않고 main이 끝나서 해당 Future 작업을 볼 수 없기에 한말
        System.out.println("supplyFuture.get() = " + supplyFuture.get());

        /**
         * 지금까지는 이전과 같음
         * ExecutorService에 callable 넘겨줘서 실행시키고
         * future.get()으로 블로킹 연산해서 결과 가져오고
         * 
         * 우리가 원하는 것은 작업이 완료되었을때
         * 비동기적으로 callback을 실행시키고 싶은 것임
         */
    }
}
