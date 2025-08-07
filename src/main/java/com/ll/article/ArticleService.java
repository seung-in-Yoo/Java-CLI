package com.ll.article;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article write(String title, String content) {
        return articleRepository.write(title, content);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(long id) {
        return articleRepository.findById(id);
    }

    public void update(int id, String title, String content) {
        articleRepository.update(id, title, content);
    }

    public void delete(int id) {
        articleRepository.delete(id);
    }
}
