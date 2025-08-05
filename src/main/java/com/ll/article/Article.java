package com.ll.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
    private int id;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
