package com.ll.article.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class Article {
    private final int id;
    private final LocalDate regDate;
    @Setter // 읽기 전용인 필드는 수정하지 못하도록 명시적으로 수정하는곳만 setter 처리
    private String title;
    @Setter // 읽기 전용인 필드는 수정하지 못하도록 명시적으로 수정하는곳만 setter 처리
    private String content;

    private Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = LocalDate.now(); // 정적 팩토리 메서드에서 날짜 생성하도록 리팩토링
    }

    public static Article of(int id, String title, String content) {
        return new Article(id, title, content);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}