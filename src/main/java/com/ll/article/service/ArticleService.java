package com.ll.article.service;

import com.ll.article.entity.Article;
import com.ll.article.repository.ArticleRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArticleService {
    private final ArticleRepository repository = new ArticleRepository();

    public Article writeArticle(String title, String content) {
        return repository.save(title, content);
    }

    public List<Article> listArticles(String order) {
        List<Article> articles = repository.findAll();

        List<Article> sorted = new ArrayList<>(articles);

        // 기본값은 최신순으로 하고, 정렬하려고 하는 입력값이 들어오면 switch문을 통해서 명령 처리
        switch ((order == null ? "number-desc" : order).toLowerCase()) {
            case "number-asc" -> sorted.sort(Comparator.comparingInt(Article::getId));
            case "number-desc" -> sorted.sort(Comparator.comparingInt(Article::getId).reversed());
            case "date-asc" -> sorted.sort(Comparator.comparing(Article::getRegDate));
            case "date-desc" -> sorted.sort(Comparator.comparing(Article::getRegDate).reversed());
            default -> sorted.sort(Comparator.comparingInt(Article::getId).reversed());
        }
        return sorted;
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