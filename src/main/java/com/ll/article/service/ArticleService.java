package com.ll.article.service;

import com.ll.article.entity.Article;
import com.ll.article.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
    private final ArticleRepository repository = new ArticleRepository();

    public Article writeArticle(String title, String content) {
        return repository.save(title, content);
    }

    public List<Article> listArticles() {
        return repository.findAll();
    }
}