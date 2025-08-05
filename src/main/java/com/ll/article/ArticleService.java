package com.ll.article;

public class ArticleService {
    ArticleRepository articleRepository;

    public ArticleService() {
        articleRepository = new ArticleRepository();
    }

    public Article write(String title, String content) {
        Article article = new Article(title, content);
        return articleRepository.save(article);
    }
}
