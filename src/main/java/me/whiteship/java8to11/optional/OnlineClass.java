package me.whiteship.java8to11.optional;

import java.util.Optional;

public class OnlineClass {

    private Integer id;
    private String title;
    private boolean closed;
    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
        // Optional 컨테이너 안에 들어갈 수 있는 인스턴스가 null일 가능성이 있을 때 ofNullable 사용
        // 그냥 of의 경우는 무조건 null이 아닐때 사용한다. -> of의 경우 null이 들어오면 NPE 발생
        return Optional.ofNullable(progress);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
