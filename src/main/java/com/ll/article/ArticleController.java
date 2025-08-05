package com.ll.article;

import java.util.List;
import java.util.Scanner;

public class ArticleController {
    // TODO: 필요한 메서드 구현
    Scanner scanner = new Scanner(System.in);
    public ArticleController(Scanner scanner) { this.scanner = scanner; }
    ArticleService articleService = new ArticleService();

    public void run(String command) {
        if (command.equals("write")) {
            write();
        }
        else if (command.equals("list")) {
            list();
        }
        else if (command.startsWith("delete")) {
            int id = Integer.parseInt(command.split(" ")[1]);
            delete(id);
        }
        else if (command.startsWith("update")) {
            int id = Integer.parseInt(command.split(" ")[1]);
            update(id);
        }
        else if (command.startsWith("detail")) {
            int id = Integer.parseInt(command.split(" ")[1]);
            detail(id);
        }
    }

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
        System.out.println("번호 | 제목          | 등록일");
        System.out.println("----------------------------------");
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i).getId() + " | " + list.get(i).getTitle() + " | " + list.get(i).getRegDate());
        }
    }

    public void detail(int id) {
        Article article = articleService.findById(id);
        if (article == null) {
            System.out.println(id + "번 글이 없습니다.");
            return;
        }
        System.out.println("번호: " + article.getId());
        System.out.println("제목: " + article.getTitle());
        System.out.println("내용: " + article.getContent());
        System.out.println("등록일: " + article.getRegDate());
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
}
