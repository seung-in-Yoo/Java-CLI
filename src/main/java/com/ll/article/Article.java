package com.ll.article;

public class Article {
    // TODO: id, title, content, regDate 등 필드 정의
    public int id;
    public String title;
    public String content;
    public String regDate;

    public Article(int id, String title, String content, String regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
    }
}
