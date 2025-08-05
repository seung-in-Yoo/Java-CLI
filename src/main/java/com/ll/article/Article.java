package com.ll.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Article {
    // TODO: id, title, content, regDate 등 필드 정의
    private int id;
    private String title;
    private String content;
    @Setter(AccessLevel.NONE)
    private final LocalDate regDate;
    private int count;

    public Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        regDate = LocalDate.now();
        count = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id == article.id &&
                title.equals(article.title) &&
                content.equals(article.content);
    }
}
