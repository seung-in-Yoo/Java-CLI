package com.ll.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles;
    private int lastId;

    ArticleRepository() {
        articles = new ArrayList<Article>();
        lastId = 0;
    }

    void addArticleToList(String title, String content) {
        Article newArticle = new Article(++lastId, title, content);
        articles.add(newArticle);
    }

    Article findArticleFromList(int id) {
        for (Article article : articles) {
            if (article.getId() == id) return article;
        }

        return null;
    }

    void deleteArticleFromList(Article article) {
        articles.remove(article);
    }

    List<Article> getArticleList() {
        return articles;
    }

}
