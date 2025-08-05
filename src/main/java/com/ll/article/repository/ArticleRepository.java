package com.ll.article.repository;

import com.ll.article.entity.Article;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    public Article save(String title, String content) {
        Article article = Article.of(++lastId, title, content);
        articles.add(article);
        return article;
    }

    public List<Article> findAll() {
        return List.copyOf(articles); // copyOf 사용하여 원본 수정 불가능하도록 처리
    }

    public Article findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null); // 찾지못하면 null
    }
}