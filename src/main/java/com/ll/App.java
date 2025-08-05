package com.ll;

import com.ll.article.ArticleController;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        ArticleController articleController = new ArticleController(sc);
        System.out.println("== 게시판 앱 만들기 ==");

        while(true) {
            System.out.print("명령어: ");
            Rq rq = new Rq(sc.nextLine());

            switch(rq.getCmd()) {
                case "write" -> articleController.actionWrite();
                case "list" -> articleController.actionList();
                case "detail" -> articleController.actionDetail(rq);
                case "delete" -> articleController.actionDelete(rq);
                case "update" -> articleController.actionModify(rq);
                case "exit" -> {
                    sc.close();
                    System.out.println("프로그램을 종료합니다.");
                    return ;
                }
            }
        }

    }
}