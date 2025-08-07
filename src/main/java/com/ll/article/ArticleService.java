package com.ll.article;

import java.util.List;

public class ArticleService {
    // TODO: 비즈니스 로직 구현
    ArticleRepository articleRepository = new ArticleRepository();

    public Article findById(int id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Boolean deleteById(int id) {
        return articleRepository.deleteById(id);
    }

    public Article write(String title, String content) {
        return articleRepository.save(title, content);
    }

    public void update(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
    }

    public void view(Article article) {
        article.setCount(article.getCount() + 1);
    }

    public List<Article> search(String keyword) {
        return articleRepository.findByTitleOrContent(keyword);
    }

    public Article writeComment(Article article, String content) {
        return articleRepository.saveComment(article, content);
    }
}
