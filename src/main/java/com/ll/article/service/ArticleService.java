package com.ll.article.service;

import com.ll.article.entity.Article;
import com.ll.article.repository.ArticleRepository;

import java.io.IOException;
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

    public void updateArticle(int id, String title, String content) {
        Article article = repository.findById(id);
        article.update(title, content);
    }

    public void deleteArticle(int id) {
        Article article = repository.findById(id);
        repository.delete(article);
    }

    public void increaseViewCount(Article article) {
        article.increaseViewCount();
    }

    public List<Article> searchArticles(String keyword) {
        List<Article> found = repository.findByKeyword(keyword);
        List<Article> reversed = new ArrayList<>(found);
        Collections.reverse(reversed);
        return reversed;
    }

    public void saveFile(String fileName) throws IOException {
        repository.saveFile(fileName);
    }
    public void loadFile(String fileName) throws IOException {
        repository.loadFile(fileName);
    }
}