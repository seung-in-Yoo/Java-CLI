package com.ll;

import com.ll.article.ArticleController;
import com.ll.article.ArticleRepository;
import com.ll.article.ArticleService;

import java.util.Scanner;

public class AppContext {
    public static Scanner scanner;
    public static ArticleRepository articleRepository;
    public static ArticleService articleService;
    public static ArticleController articleController;

    public static void renew(Scanner _scanner) {
        scanner = _scanner;
        articleRepository = new ArticleRepository();
        articleService = new ArticleService();
        articleController = new ArticleController();
    }

    public static void renew() {
        renew(new Scanner(System.in));
    }
}
