package com.ll.article;

import java.util.Scanner;

public class ArticleService {
    private final Scanner sc;
    private ArticleRepository articleRepository;


    public ArticleService(Scanner sc) {
        this.sc = sc;
        articleRepository = new ArticleRepository();
    }

    Article writeArticle() {
        return null;
    }

    void listArticle() {

    }

    void showDetails(int id) {
    }

    void updateArticle(int id) {
    }

    void deleteArticle(int id) {
    }




}
