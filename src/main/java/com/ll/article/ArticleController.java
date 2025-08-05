package com.ll.article;

import java.util.List;
import java.util.Scanner;

public class ArticleController {

    ArticleService articleService;
    Scanner scanner;

    public ArticleController(Scanner scanner) {
        articleService = new ArticleService();
        this.scanner = scanner;
    }

    public void actionWrite() {
        System.out.print("제목 : ");
        String title = scanner.nextLine();
        System.out.print("내용 : ");
        String content = scanner.nextLine();
        Article article = articleService.write(title, content);
        System.out.println(article.getId() + "번 게시글이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println(" 번호 | 제목 | 등록일 ");
        System.out.println("----------------------");
        List<Article> articles = articleService.getList();
        for (Article article : articles) {
            System.out.println(article.getId() + " | " + article.getTitle() + " | " + article.getRegDate());
        }
    }

    public void actionExit() {
        System.out.println("프로그램을 종료합니다.");
    }
}
