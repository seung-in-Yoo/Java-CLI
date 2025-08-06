package com.ll.article.controller;

import com.ll.Rq;
import com.ll.article.entity.Article;
import com.ll.article.service.ArticleService;

import java.io.IOException;
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

        try {
            Article article = service.findArticleById(id);
            if (article == null) {
                // NullPointerException 대신 명시적으로 예외 처리
                throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID=" + id);
            }

            service.increaseViewCount(article);

            System.out.println("번호: " + article.getId());
            System.out.println("제목: " + article.getTitle());
            System.out.println("내용: " + article.getContent());
            System.out.println("등록일: " + article.getRegDate());
            System.out.println("조회수: " + article.getViewCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 게시글 수정
    public void update(Rq rq) {
        int id = rq.getParamId();

        try {
            Article article = service.findArticleById(id);
            if (article == null) {
                // NullPointerException 대신 명시적으로 예외 처리
                throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID=" + id);
            }

            System.out.printf("제목 (현재: %s): ", article.getTitle());
            String newTitle = sc.nextLine().trim();

            System.out.printf("내용 (현재: %s): ", article.getContent());
            String newContent = sc.nextLine().trim();

            service.updateArticle(id, newTitle, newContent);
            System.out.println("=> 게시글이 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 게시글 삭제
    public void delete(Rq rq) {
        int id = rq.getParamId();

        try {
            Article article = service.findArticleById(id);
            if (article == null) {
                // NullPointerException 대신 명시적으로 예외 처리
                throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. ID=" + id);
            }
            service.deleteArticle(id);
            System.out.println("=> 게시글이 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 키워드 검색
    public void search(Rq rq) {
        String keyword = rq.getKeyword();

        List<Article> found = service.searchArticles(keyword);

        System.out.println("번호 | 제목       | 등록일");
        System.out.println("-----------------------------");
        for (Article article : found) {
            System.out.printf("%d    | %s | %s\n", article.getId(), article.getTitle(), article.getRegDate());
        }

        if (found.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        }
    }

    // 파일 저장
    public void save() {
        try {
            service.saveFile("articles.txt");
            System.out.println("저장되었습니다.");
        } catch (IOException e) {
            System.out.println("저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 파일 불러오기
    public void load() {
        try {
            service.loadFile("articles.txt");
            System.out.println("불러오기 완료.");
        } catch (IOException e) {
            System.out.println("불러오기 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}