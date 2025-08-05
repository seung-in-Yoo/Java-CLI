package com.ll.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    // TODO: 게시글 저장 및 조회 등 기능 구현
    private final List<Article> articles = new ArrayList<Article>(); // 데이터베이스 역할
    private int lastId = 0; // 데이터베이스 내 primary key (id) 역할

    public Article save(String title, String content) {
        lastId++;
        Article article = new Article(lastId, title, content);
        articles.add(article);
        return article;
    }

    public Article findById(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public List<Article> findAll() {
        return articles;
    }

    public Boolean deleteById(int id) {
        for (Article article : articles) {
            if (article.getId() == id) {
                articles.remove(article);
                return true;
            }
        }
        return false;
    }

    public List<Article> findByTitleOrContent(String keyword) {
        List<Article> searchArticles = new ArrayList<>();
        for (Article article : articles) {
            if (article.getTitle().contains(keyword) || article.getContent().contains(keyword)) {
                searchArticles.add(article);
            }
        }
        return searchArticles;
    }
}
