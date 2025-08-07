package com.ll;

import com.ll.article.ArticleController;
import com.ll.article.ArticleRepository;
import com.ll.article.ArticleService;

import java.util.Scanner;

public class App {
    public void run() {
        System.out.println("== 명언 앱 ==");
        Scanner sc = new Scanner(System.in);

        ArticleRepository articleRepository = new ArticleRepository();
        ArticleService articleService = new ArticleService(articleRepository);
        ArticleController articleController = new ArticleController(sc, articleService);

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "exit":
                    sc.close();
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case "write":
                    articleController.write();
                    break;
                case "list":
                    articleController.list();
                    break;
                case "detail":
                    articleController.detail(rq);
                    break;
                case "update":
                    articleController.update(rq);
                    break;
                case "delete":
                    articleController.delete(rq);
                    break;
                default:
                    System.out.println("올바르지 않은 명령어입니다.");
                    break;
            }
        }
    }
}
