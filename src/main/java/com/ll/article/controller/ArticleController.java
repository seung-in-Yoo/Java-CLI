package com.ll.article.controller;

import com.ll.Rq;
import com.ll.article.entity.Article;
import com.ll.article.service.ArticleService;

import java.util.List;
import java.util.Scanner;

public class ArticleController {
    private final Scanner sc;
    private final ArticleService service;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        this.service = new ArticleService();
    }

    // 게시글 작성
    public void write() {
        System.out.print("제목: ");
        String title = sc.nextLine().trim();

        System.out.print("내용: ");
        String content = sc.nextLine().trim();

        Article article = service.writeArticle(title, content);

        System.out.println("=> 게시글이 등록되었습니다.");
        System.out.printf("번호: %d, 등록일: %s\n", article.getId(), article.getRegDate());
    }

    // 게시글 목록 출력
    public void list() {
        List<Article> articles = service.listArticles();

        System.out.println("번호 | 제목       | 등록일");
        System.out.println("-----------------------------");
        for (Article article : articles) {
            System.out.printf("%d    | %s | %s\n", article.getId(), article.getTitle(), article.getRegDate());
        }
    }

    // 게시글 상세 보기
    public void detail(Rq rq) {
        int id = rq.getParamId();
        Article article = service.findArticleById(id);

        if (article == null) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
            return;
        }

        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("등록일: " + article.getRegDate());
    }

    // 게시글 수정
    public void update(Rq rq) {
        int id = rq.getParamId();
        Article article = service.findArticleById(id);

        if (article == null) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
            return;
        }

        System.out.printf("제목 (현재: %s): ", article.getTitle());
        String newTitle = sc.nextLine().trim();

        System.out.printf("내용 (현재: %s): ", article.getContent());
        String newContent = sc.nextLine().trim();

        service.updateArticle(id, newTitle, newContent);
        System.out.println("=> 게시글이 수정되었습니다.");
    }

    // 게시글 삭제
    public void delete(Rq rq) {
        int id = rq.getParamId();
        Article article = service.findArticleById(id);

        if (article == null) {
            System.out.println("해당 게시글이 존재하지 않습니다.");
            return;
        }

        service.deleteArticle(id);
        System.out.println("=> 게시글이 삭제되었습니다.");
    }
}