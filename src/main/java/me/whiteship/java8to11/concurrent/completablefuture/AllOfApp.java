package me.whiteship.java8to11.concurrent.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class AllOfApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        /**
         * 인자로 전달된 모든 서브테스크들이 완료되었을 때 콜백 실행
         * 근데 문제점은, 여기서 전달된 서브테스크들 중 특정한 결과를 가져올 수 없는게,
         * 결과의 타입이 모두 같다는 보장도 없고, 심지어 에러를 throw하는 경우도 있다.
         * 결과값이라는 것 자체가 무의미함 (결과가 null임)
         * 제대로 받으려면 굉장히 복잡함
         */
        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> {
                    // thenApply하는 시점에는 이미 모든 Future의 작업이 끝남
                    // 그말은 즉슨 여기서 get으로 결과값 가져올 수 있음
                    // get의 경우 checked exception이 발생함, 따라서 try catch로 에러처리하기 굉장히 번거로움
                    // 반면에 join()의 경우 unchecked exception이 발생함. 따라서 에러처리하기 상대적으로 수월
                    return futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList());
                });

        results.get().forEach(System.out::println);
    }
}
