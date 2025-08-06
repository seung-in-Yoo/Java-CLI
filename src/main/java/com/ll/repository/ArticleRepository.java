package com.ll.repository;


import com.ll.article.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;


    public Article save(String title, String content, String regDate) {
        Article a = new Article(++lastId, title, content, regDate);
        articles.add(a);
        return a;
    }


    public List<Article> findAll() {
        return articles;
    }


    public Article findById(int id) {
        return articles.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public boolean delete(int id) {
        return articles.removeIf(a -> a.getId() == id);
    }
}
