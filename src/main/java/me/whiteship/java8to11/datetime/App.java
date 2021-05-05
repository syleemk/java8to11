package me.whiteship.java8to11.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class App {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 기존에 있던 Date관련 라이브러리
         * 여러 불편한 점들이 많았음
         */
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        /**
         * mutable하다. 객체의 "상태"를 바꿀 수 있다.
         * mutalble한 객체들은 멀티쓰레드 환경에서 안전하게 쓰기 어렵다.
         */
        long time = date.getTime();
        Thread.sleep(1000 * 3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);
        //객체 상태 바꿀 수 있다.
        after3Seconds.setTime(time);
        System.out.println(after3Seconds);

        /**
         * Calender객체의 경우 Month에 숫자사용하면 안됨 (0부터 시작)
         * 따라서 버그 발생하기 쉬움. enum을 사용해야함. (Calender.JULY)
         * 생성자 파라미터 타입보면 다 int만 받게 해놓음 -> 다 enum만 받게 해놓으면 타입 안정성있음
         * 아무 값이나 다 들어올 수 있다. int기 때문에, -100 이런 음수 값도 들어올 수 있음.
         * (0부터 11까지의 값만 들어와야하는데 그게 보장이 안된다.)
         * 이러한 것을 type safe하지 않다고함
         * 이거를 type safe하게 받는 다는 것은 int보다는 Month라는 enum 타입을 받아서 
         * 해당하는 타입만 들어오도록 하는 것
         */
        Calendar sooyoungBirthday = new GregorianCalendar(1995, 11, 7);
    }
}
