package com.ll.article.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class Article {
    private final int id;

    // 읽기 전용인 필드는 수정하지 못하도록 명시적으로 수정하는곳만 setter 처리
    @Setter
    private String title;
    @Setter
    private String content;

    private final LocalDate regDate;


    private Article(int id, String title, String content, LocalDate regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
    }

    public static Article of(int id, String title, String content) {
        return new Article(id, title, content, LocalDate.now());
    }
}