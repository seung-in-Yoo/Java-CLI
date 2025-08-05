package com.ll;

import com.ll.article.ArticleController;
import com.ll.article.ArticleService;

import java.util.Scanner;

public class App {
    Scanner scanner;

    public App(Scanner scanner) {
        this.scanner = scanner;
    }
    public void run() {
        ArticleController articleController = new ArticleController(scanner);

        System.out.println("== 게시판 앱 만들기 ==");
        while(true) {
            System.out.print("명령어 : ");
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "write" -> articleController.actionWrite();
                case "exit" -> {
//                    articleController.actionExit();
                    return;
                }
            }
        }
    }
}