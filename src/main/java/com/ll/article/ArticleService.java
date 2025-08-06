package com.ll.article;

import com.ll.AppContext;

import java.util.List;

public class ArticleService {
    ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = AppContext.articleRepository;
    }

    public Article write(String title, String content) {
        Article article = new Article(title, content);
        return articleRepository.save(article);
    }

    public List<Article> getList() {
        return articleRepository.getList();
    }

    public Article getArticle(int id) {
        return articleRepository.getArticle(id);
    }

    public void update(Article article, String updateTitle, String updateContent) {
        article.setTitle(updateTitle);
        article.setContent(updateContent);
        articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }
}
