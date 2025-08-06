package com.ll;

import com.ll.article.ArticleController;
import com.ll.article.ArticleService;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
    Scanner scanner;

    public App() {
        this.scanner = AppContext.scanner;
    }
    public void run() {
        ArticleController articleController = AppContext.articleController;

        System.out.println("== 게시판 앱 만들기 ==");
        while(true) {
            System.out.print("명령어 : ");
            String cmd = scanner.nextLine();
            Rq rq = new Rq(cmd);
            switch (rq.getActionName()) {
                case "write" -> articleController.actionWrite();
                case "list" -> articleController.actionList();
                case "detail" -> articleController.actionDetail(rq);
                case "update" -> articleController.actionUpdate(rq);
                case "delete" -> articleController.actionDelete(rq);
                case "exit" -> {
                    articleController.actionExit();
                    return;
                }
            }
        }
    }
}