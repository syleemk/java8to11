package me.whiteship.java8to11.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

public class App2 {

    public static void main(String[] args) {
        /**
         * 기계 시간 (epoch time) : Instant 출력하면 사용자 친화적인 시간으로 출력해줌
         * 기계용 API
         * - 스탑워치처럼 시간을 재거나
         * - 메서드 실행시간을 비교하거나 할 때 사용
         */
        Instant instant = Instant.now();
        System.out.println("instant = " + instant); // 기준시 UTC, GMT
        System.out.println("instant = " + instant.atZone(ZoneId.of("UTC"))); // 기준시 UTC, GMT

        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zone);// 내가 속한 지역 시간 적용
        System.out.println("zone = " + zone);
        System.out.println("zonedDateTime = " + zonedDateTime);

        /**
         * 인류용 일시 표현
         * Local이 붙어있기 때문에 내 시스템 지역 정보 참고해서 로컬 시간 가져옴
         * 만약 서버에 배포를하는데, 해당 서버가 미국에 있다면,
         * 해당 서버의 시스템 정보를 참고해서 미국 시간대를 가져온다.
         */
        LocalDateTime now = LocalDateTime.now(); // 현재 시간 가져옴
        System.out.println("now = " + now);

        LocalDateTime birthday =
                LocalDateTime.of(1995, Month.NOVEMBER, 15, 0, 0, 0);

        /**
         * ZonedDateTime, Instant, LocalDateTime 서로 변환 가능
         */
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("nowInKorea = " + nowInKorea);

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedInstant = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println("zonedInstant = " + zonedInstant);

        /**
         * 기간을 표현하는 방법
         * Period/Duration .between()
         * Period - 인류용 시간 비교
         * Duration - 기계용 시간 비교
         */
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.NOVEMBER, 7);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println("today = " + today);
        System.out.println("thisYearBirthday = " + thisYearBirthday);
        System.out.println("period.getDays() = " + period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println("until.get(ChronoUnit.DAYS) = " + until.get(ChronoUnit.DAYS));

        // 기계 시간 비교 - Duration & Instant
        Instant now2 = Instant.now();
        Instant plus = now2.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now2, plus);
        System.out.println("between.getSeconds() = " + between.getSeconds());

        /**
         * 파싱 또는 포매팅
         */
        LocalDateTime now1 = LocalDateTime.now();
        // 커스텀하게 날짜 포맷팅
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("now1 = " + now1);
        System.out.println("now1.format(MMddyyyy) = " + now1.format(MMddyyyy));

        //파싱
        LocalDate parse = LocalDate.parse("11/07/1995", MMddyyyy);
        System.out.println("parse = " + parse);

        /**
         * 레거시 API 지원
         * 레거시 API <-> Java8 날짜 API 상호변환 가능
         */
        Date date = new Date();
        // Date -> Instant
        Instant instant1 = date.toInstant();
        // Instant -> Date
        Date newDate = Date.from(instant1);

        // gregorianCalender -> localDateTime or ZonedDateTime
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        LocalDateTime localDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        ZonedDateTime zonedDateTime1 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());

        // ZonedDateTime -> gregorianCalender
        GregorianCalendar from = GregorianCalendar.from(zonedDateTime1);
    }
}
