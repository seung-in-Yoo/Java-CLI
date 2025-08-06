package com.ll.article.service;

import com.ll.article.entity.Article;
import com.ll.article.entity.ArticleSortOption;
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

    public List<Article> listArticles(ArticleSortOption option) {
        List<Article> articles = repository.findAll();
        List<Article> sorted = new ArrayList<>(articles);

        if (option == null) { option = ArticleSortOption.NUMBER_DESC; } // 기본값: 최신순

        switch (option) {
            case NUMBER_ASC -> sorted.sort(Comparator.comparingInt(Article::getId));
            case NUMBER_DESC -> sorted.sort(Comparator.comparingInt(Article::getId).reversed());
            case DATE_ASC -> sorted.sort(
                    Comparator.comparing(Article::getRegDate)
                            .thenComparing(Article::getId)
            );
            case DATE_DESC -> sorted.sort(
                    Comparator.comparing(Article::getRegDate).reversed()
                            .thenComparing(Article::getId, Comparator.reverseOrder())
            );
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