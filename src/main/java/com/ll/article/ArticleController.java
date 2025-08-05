package com.ll.article;

import com.ll.Rq;

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

    public void actionDetail(Rq rq) {
        if (rq.getActionId() == -1) {
            System.out.println("검색할 게시글의 id를 입력해주세요.");
            return;
        }
        Article article = articleService.getArticle(rq.getActionId());
        if (article == null) {
            System.out.println(rq.getActionId() + "번 게시글은 없습니다.");
            return;
        }
        System.out.println("번호 : " + article.getId());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getContent());
        System.out.println("등록일 : " + article.getRegDate());
    }

    public void actionUpdate(Rq rq) {
        if (rq.getActionId() == -1) {
            System.out.println("수정할 게시글의 id를 입력해주세요.");
            return;
        }
        Article article = articleService.getArticle(rq.getActionId());
        if (article == null) {
            System.out.println(rq.getActionId() + "번 게시글은 없습니다.");
            return;
        }
        System.out.print("제목 (현재: " + article.getTitle() + ") : ");
        String updateTitle = scanner.nextLine();
        System.out.print("내용 (현재: " + article.getContent() + ") : ");
        String updateContent = scanner.nextLine();
        articleService.update(article, updateTitle, updateContent);
        System.out.println(article.getId() + "번 게시글이 수정되었습니다.");
    }

    public void actionDelete(Rq rq) {
        if (rq.getActionId() == -1) {
            System.out.println("삭제할 게시글의 id를 입력해주세요.");
            return;
        }
        Article article = articleService.getArticle(rq.getActionId());
        if (article == null) {
            System.out.println(rq.getActionId() + "번 게시글은 없습니다.");
            return;
        }
        articleService.delete(article);
        System.out.println(article.getId() + "번 게시글이 삭제되었습니다.");
    }

    public void actionExit() {
        System.out.println("프로그램을 종료합니다.");
    }
}
