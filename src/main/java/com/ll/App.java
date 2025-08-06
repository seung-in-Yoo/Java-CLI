package com.ll;

import com.ll.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    private final List<Article> articles = new ArrayList<Article>();
    private int lastId = 0;
    public void run() {
        System.out.println("== 게시판 앱 만들기 ==");
    }
    public List<Article> getArticles() {
        return articles;
    }
    public void write(String title, String content, String regDate) {
        Article article = new Article(++lastId, title, content, regDate);
        articles.add(article);

    }
}