package com.ll.article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    List<Article> articles = new ArrayList<>();
    int lastId = 0;

    public Article save(Article article) {
        if (article.isNew()) {
            article.setId(++lastId);
            article.setRegDate(String.valueOf(LocalDate.now()));
            articles.add(article);
        }
        return article;
    }

    public List<Article> getList() {
        return articles.reversed();
    }

    public Article getArticle(int id) {
        return articles.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
