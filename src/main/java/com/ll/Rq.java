package com.ll;

import com.ll.article.ArticleController;

import java.util.Scanner;

public class Rq {
    // TODO: 사용자 입력 유틸 구현
    Scanner scanner = new Scanner(System.in);
    public Rq (Scanner scanner) {
        this.scanner = scanner;
    }
    ArticleController articleController = new ArticleController(scanner);

    public void run(String command) {
        if (command.equals("write")) {
            articleController.write();
        }
        else if (command.equals("list")) {
            articleController.list();
        }
        else if (command.startsWith("list")) {
            String sort = parseId(command);
            if (sort != null) {
                articleController.list(sort);
            } else articleController.list();
        }
        else if (command.startsWith("delete")) {
            int id = Integer.parseInt(parseId(command));
            if (id > 0)
                articleController.delete(id);
            else System.out.println("잘못된 명령어입니다.");
        }
        else if (command.startsWith("update")) {
            int id = Integer.parseInt(parseId(command));
            if (id > 0)
                articleController.update(id);
            else System.out.println("잘못된 명령어입니다.");
        }
        else if (command.startsWith("detail")) {
            int id = Integer.parseInt(parseId(command));
            if (id > 0)
                articleController.detail(id);
            else System.out.println("잘못된 명령어입니다.");
        }
        else if (command.startsWith("search")) {
            String id = parseId(command);
            if (id != null)
                articleController.search(id);
            else System.out.println("잘못된 명령어입니다.");
        }
    }

    public String parseId(String command) {
        String[] parsed = command.split(" ");
        if (parsed.length != 2) return null;
        else return (parsed[1]);
    }
}
