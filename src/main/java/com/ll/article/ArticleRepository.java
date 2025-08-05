package com.ll.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    List<Article> articles = new ArrayList<>();
    int lastId = 0;

    public Article save(Article article) {
        article.setId(++lastId);
        articles.add(article);
        return article;
    }
}
