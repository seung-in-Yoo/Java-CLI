package com.ll.article.repository;

import com.ll.article.entity.Article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    public Article save(String title, String content) {
        Article article = Article.of(++lastId, title, content, LocalDate.now());
        articles.add(article);
        return article;
    }
}