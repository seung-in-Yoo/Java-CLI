package com.ll.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private final List<Article> articles;
    private int lastId;

    ArticleRepository() {
        articles = new ArrayList<Article>();
        lastId = 1;
    }

    Article addArticleToList(String title, String content) {
        return null;
    }

    Article findArticleFromList(int id) {
        return null;
    }

    Article updateArticleInList(Article article) {
        return null;
    }

    void deleteArticleFromList(int id) {
    }

    void showArticleList() {

    }

}
