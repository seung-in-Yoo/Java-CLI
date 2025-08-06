package com.ll;

import com.ll.setting.SettingsController;
import com.ll.article.ArticleController;

import java.util.Scanner;

public class App {
    public void run(Scanner sc) {
        ArticleController articleController = new ArticleController(sc);
        SettingsController appSettingsController = new SettingsController();
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
                case "setting" -> appSettingsController.actionSetting(rq);
                case "exit" -> {
                    sc.close();
                    System.out.println("프로그램을 종료합니다.");
                    return ;
                }
                default -> {
                    System.out.println("없는 명령어입니다.");
                }
            }

            System.out.println();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        run(sc);
    }
}