package com.ll.article.service;

import com.ll.article.entity.Article;
import com.ll.article.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleService {
    private final ArticleRepository repository = new ArticleRepository();

    public Article writeArticle(String title, String content) {
        return repository.save(title, content);
    }

    public List<Article> listArticles() {
        List<Article> articles = repository.findAll();
        List<Article> reversed = new ArrayList<>(articles);
        Collections.reverse(reversed);
        return reversed;
    }

    public Article findArticleById(int id) {
        return repository.findById(id);
    }
}