package com.ll.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles;
    private long lastId;

    public ArticleRepository() {
        this.articles = new ArrayList<>();
        this.lastId = 0;
    }

    public Article write(String title, String content) {
        long id = ++lastId;
        Article article = new Article(id, title, content);
        articles.add(article);
        return article;
    }

    public List<Article> findAll() {
        return articles;
    }

    public Article findById(long id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public void update(int id, String title, String content) {
        Article article = findById(id);

        if (article != null) {
            article.setTitle(title);
            article.setContent(content);
        }
    }

    public void delete(int id) {
        Article article = findById(id);

        if (article != null) {
            articles.remove(article);
        }
    }
}
