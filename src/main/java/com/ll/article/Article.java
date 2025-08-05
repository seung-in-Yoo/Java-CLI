package com.ll.article;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article {
    private final int id;
    private String title;
    private String content;
    private final String regDate;

    Article(int id, String title, String content) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String getRegDate() {
        return regDate;
    }
}
