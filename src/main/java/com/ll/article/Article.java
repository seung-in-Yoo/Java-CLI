package com.ll.article;

import java.time.LocalDate;

public class Article {
    // TODO: id, title, content, regDate 등 필드 정의
    private int id;
    private String title;
    private String content;
    private final LocalDate regDate;

    public Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        regDate = LocalDate.now();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getRegDate() {
        return regDate.toString();
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
