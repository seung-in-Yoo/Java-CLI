package com.ll.article;

import java.util.List;
import java.util.Scanner;

public class ArticleService {
    private final Scanner sc;
    private ArticleRepository articleRepository;


    public ArticleService(Scanner sc) {
        this.sc = sc;
        articleRepository = new ArticleRepository();
    }

    void writeArticle(String title, String content) {
        articleRepository.addArticleToList(title, content);
        System.out.println("=> 게시글이 등록되었습니다.");
    }

    void listArticle() {
       List<Article> articleList = articleRepository.getArticleList();
       System.out.println("번호 | 제목       | 등록일");
       System.out.println("-----------------------------");
       for (Article article : articleList) {
            System.out.printf("%-5d| %s  | %s\n", article.getId(), article.getTitle(), article.getRegDate());
       }
    }

    void showDetails(int id) {
        Article foundArticle = articleRepository.findArticleFromList(id);
        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
            return ;
        }
        System.out.printf("번호: %d\n",  foundArticle.getId());
        System.out.printf("제목: %s\n",   foundArticle.getTitle());
        System.out.printf("내용: %s\n",   foundArticle.getContent());
        System.out.printf("등록일: %s\n", foundArticle.getRegDate());
    }

    void updateArticle(int id) {
        Article foundArticle = articleRepository.findArticleFromList(id);
        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
            return ;
        }

        System.out.printf("제목 (현재: %s): ", foundArticle.getTitle());
        String newTitle = sc.nextLine();
        System.out.printf("내용 (현재: %s): ", foundArticle.getContent());
        String newContent = sc.nextLine();

        foundArticle.setTitle(newTitle);
        foundArticle.setContent(newContent);
        System.out.println("=> 게시글이 수정되었습니다.");
    }

    void deleteArticle(int id) {
        Article foundArticle = articleRepository.findArticleFromList(id);
        if (foundArticle == null) {
            System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
            return ;
        }

        articleRepository.deleteArticleFromList(foundArticle);
        System.out.println("=> 게시글이 삭제되었습니다.");
    }




}
