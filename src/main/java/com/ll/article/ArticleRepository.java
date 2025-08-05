package com.ll.article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    List<Article> articles = new ArrayList<>();
    int lastId = 0;

    public Article save(Article article) {
        article.setId(++lastId);
        article.setRegDate(String.valueOf(LocalDate.now()));
        articles.add(article);
        return article;
    }
}
