package me.whiteship.java8to11.concurrent;

public class App {

    public static void main(String[] args) throws InterruptedException {

        MyTread myTread1 = new MyTread();
        // Thread.start()메서드는 JVM을 통해 쓰레드를 생성하고 오버라이딩된 run()메소드를 호출하는 것
        myTread1.start();

        /**
         * 쓰레드 생성방법2 - Runnable 인터페이스 구현한 람다식을 Thread의 생성자 파라미터로 전달
         * 참고로 람다식은 인터페이스의 구현과 인스턴스화를 동시에 처리하는 것
         */
        Thread myThread2 = new Thread(() -> {
            try {
                /**
                 * 현재 쓰레드를 잠시 멈춰두고 다른 쓰레드에게 우선권을 넘기는 메소드
                 */
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                /**
                 * InterruptedException은 쓰레드가 자는 동안에 (sleep으로 멈춰있는 동안에)
                 * 다른 누군가가 이 쓰레드를 깨우면
                 * 이 블럭 안으로 들어온다. -> 이 말은 즉슨 자고있는 쓰레드를 깨우는 방법이 있다는 뜻
                 */
                e.printStackTrace();
            }
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        myThread2.start();

        Thread myThread3 = new Thread(()->{
            while (true) {
                try {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    /**
                     * 무한루프 돌다가 누군가가 나에게 인터럽트 주어서 깨우면 종료시켜버림
                     */
                    System.out.println("exit!");
                    return;
                }
            }
        });
        myThread3.start();

        Thread myThread4 = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                // do something
                // unchecked exception = runtime exception
                throw new IllegalStateException(e);
            }
        });
        myThread4.start();

        /**
         * Thread.join()은 해당 쓰레드가 끝날때까지 기다린다.
         * join()도 sleep과 마찬가지로 쓰레드가 끝나기를 대기함
         * 대기하는 도중에 누군가가 대기하고 있는 쓰레드 (여기선 main 쓰레드)를 interrupt 시킨다면
         * 그럼 또 여기서 interruptedException이 발생하게 된다.
         */
        try {
            myThread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myThread4 + " is finished");

        System.out.println("Thread: " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        /**
         * 인터럽트 자체는 쓰레드를 종료시키는 오퍼레이션이 아님
         * 쓰레드를 깨우는 오퍼레이션이지, 종료시키기로 한 것은 우리가 결정한 것
         */
        myThread3.interrupt();
    }

    /**
     * 쓰레드를 만드는 방법1 - Thread 상속
     */
    static class MyTread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }

}
