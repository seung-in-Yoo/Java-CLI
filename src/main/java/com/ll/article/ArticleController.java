package com.ll.article;
import com.ll.Rq;
import java.util.Scanner;

public class ArticleController {
    private Scanner sc;
    private ArticleService articleService;

    public ArticleController(Scanner sc) {
        articleService = new ArticleService(sc);
        this.sc = sc;
    }

    public void actionWrite() {
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();

        articleService.writeArticle(title, content);
    }

    public void actionList() {
        articleService.listArticle();
    }

    public void actionDetail(Rq rq) {
        int findId = rq.getId(0);
        if (findId == 0) {
            System.out.printf("=> %s 명령어는 아이디와 함께 입력해야합니다.\n", rq.getCmd());
            return ;
        }
        articleService.showDetails(findId);
    }

    public void actionModify(Rq rq) {
        int findId = rq.getId(0);
        if (findId == 0) {
            System.out.printf("=> %s 명령어는 아이디와 함께 입력해야합니다.\n", rq.getCmd());
            return ;
        }

        articleService.updateArticle(findId);
    }

    public void actionDelete(Rq rq) {
        int findId = rq.getId(0);
        if (findId == 0) {
            System.out.printf("=> %s 명령어는 아이디와 함께 입력해야합니다.\n", rq.getCmd());
            return ;
        }

        articleService.deleteArticle(findId);
    }

    public void actionSearch(Rq rq) {
        articleService.searchArticle(rq.getKeyword(""));
    }
}
