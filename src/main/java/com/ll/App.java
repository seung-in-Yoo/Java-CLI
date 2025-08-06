package com.ll;

import com.ll.article.controller.ArticleController;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        ArticleController controller = new ArticleController(sc);
        System.out.println("== 게시판 앱 만들기 ==");

        while (true) {
            System.out.print("명령어: ");
            String command = sc.nextLine().trim();

            if (handleCommand(command,controller)) { break; }
        }
    }

    public boolean handleCommand(String command, ArticleController controller) {
        Rq rq = new Rq(command);
        String action = rq.getActionName();

        switch (action) {
            case "exit" -> {
                System.out.println("프로그램을 종료합니다.");
                return true;
            }
            case "write" -> controller.write();
            case "list" -> controller.list();
            case "detail" -> controller.detail(rq);
            case "update" -> controller.update(rq);
            case "delete" -> controller.delete(rq);
            case "search" -> controller.search(rq);
            case "save" -> controller.save();
            case "load" -> controller.load();
            default -> System.out.println("정확한 명령어를 입력해주세요.");
        }
        return false;
    }
}