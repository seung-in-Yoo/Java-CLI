package com.ll.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    // TODO: 게시글 저장 및 조회 등 기능 구현
    private final List<Article> articles = new ArrayList<Article>();
    private int lastId = 0;

    public Article save(String title, String content) {
        lastId++;
        Article article = new Article(lastId, title, content);
        articles.add(article);
        return article;
    }

}
