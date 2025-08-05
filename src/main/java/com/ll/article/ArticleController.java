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

        articleService.showDetails(findId);
    }

    public void actionModify(Rq rq) {
        articleService.updateArticle(rq.getId(0));
    }

    public void actionDelete(Rq rq) {
        articleService.deleteArticle(rq.getId(0));
    }
}
