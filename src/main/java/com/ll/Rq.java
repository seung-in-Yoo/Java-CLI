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
        else if (command.startsWith("delete")) {
            int id = Integer.parseInt(command.split(" ")[1]);
            articleController.delete(id);
        }
        else if (command.startsWith("update")) {
            int id = Integer.parseInt(command.split(" ")[1]);
            articleController.update(id);
        }
        else if (command.startsWith("detail")) {
            int id = Integer.parseInt(command.split(" ")[1]);
            articleController.detail(id);
        }
    }
}
