package com.ll.article;

import com.ll.comment.Comment;

import java.util.List;
import java.util.Scanner;

public class ArticleController {
    // TODO: 필요한 메서드 구현
    Scanner scanner = new Scanner(System.in);
    public ArticleController(Scanner scanner) { this.scanner = scanner; }
    ArticleService articleService = new ArticleService();

    public void write() {
        System.out.print("제목: ");
        String title = scanner.nextLine();
        System.out.print("내용: ");
        String content = scanner.nextLine();
        Article article = articleService.write(title, content);
        System.out.println("게시글이 등록되었습니다.");
    }

    public void list() {
        List<Article> list = articleService.findAll();
        printListDesc(list);
    }

    public void list(String sort) {
        List<Article> list = articleService.findAll();
        if (sort.equals("asc")) {
            printListAsc(list);
        } else if (sort.equals("desc")) {
            printListDesc(list);
        }
    }

    public void detail(int id) {
        Article article = articleService.findById(id);
        if (article == null) {
            System.out.println(id + "번 글이 없습니다.");
            return;
        }
        articleService.view(article); // 조회
        printDetail(article);
        while (true) {
            System.out.println("[comment + 댓글]을 입력하시면 댓글 입력 가능 / 이외 입력 시 뒤로");
            String command = scanner.nextLine();
            if (command.startsWith("comment")) {
                articleService.writeComment(article, command.split(" ")[1]);
                printDetail(article);
            } else return;
        }
    }

    public void update (int id) {
        Article article = articleService.findById(id);
        if (article == null) {
            System.out.printf("%d번 글이 없습니다.\n", id);
            return;
        }
        System.out.printf("제목 (현재: %s): ", article.getTitle());
        String newTitle = scanner.nextLine();
        System.out.printf("내용 (현재: %s): ", article.getContent());
        String newContent = scanner.nextLine();

        articleService.update(article, newTitle, newContent);
    }

    public void delete (int id) {
        Boolean result = articleService.deleteById(id);
        if (result) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }
    }

    public void search (String keyword) {
        List<Article> articles = articleService.search(keyword);
        if (articles.isEmpty()) {
            System.out.println("해당하는 게시물이 없습니다.");
        } else {
            printListDesc(articles);
        }
    }

    private void printListDesc (List<Article> articles) {
        System.out.println("번호 | 제목 | 등록일 | 조회수");
        System.out.println("----------------------------------");
        for (int i = articles.size() - 1; i >= 0; i--) {
            System.out.println(articles.get(i).getId() + " | " + articles.get(i).getTitle() + " | " + articles.get(i).getRegDate() + " | " + articles.get(i).getCount());
        }
    }
    private void printListAsc (List<Article> articles) {
        System.out.println("번호 | 제목 | 등록일 | 조회수");
        System.out.println("----------------------------------");
        for (Article article : articles) {
            System.out.println(article.getId() + " | " + article.getTitle() + " | " + article.getRegDate() + " | " + article.getCount());
        }
    }

    private void printDetail (Article article) {
        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("등록일: " + article.getRegDate());
        System.out.println("조회수: " + article.getCount());
        System.out.println("================ 댓글 ================");
        for (Comment comment : article.getComments()) {
            System.out.println(comment.getId() + ". " + comment.getContent() + " | " + comment.getRegDate());
        }
    }
}
