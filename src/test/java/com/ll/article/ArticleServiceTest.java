package com.ll.article;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ArticleServiceTest {
    @Test
    @DisplayName("게시글 작성 - input")
    void t1() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                """;

        ArticleService articleService = new ArticleService(new Scanner(input));
        articleService.writeArticle();

        assertThat(out.toString())
                .contains("자바 공부")
                .contains("자바 텍스트 게시판 만들기");
    }

    @Test
    @DisplayName("게시글 작성 - 결과 확인 - 제목 등록")
    void t2() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                """;

        ArticleService articleService = new ArticleService(new Scanner(input));
        Article newArticle = articleService.writeArticle();

        assertThat(newArticle.getTitle()).isEqualTo("자바 공부");
    }

    @Test
    @DisplayName("게시글 작성 - 결과 확인 - 내용 등록")
    void t3() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                """;

        ArticleService articleService = new ArticleService(new Scanner(input));
        Article newArticle = articleService.writeArticle();

        assertThat(newArticle.getContent()).isEqualTo("자바 텍스트 게시판 만들기");
    }

    @Test
    @DisplayName("게시글 목록 보기")
    void t4() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                """;

        ArticleService articleService = new ArticleService(new Scanner(input));
        articleService.writeArticle();
        articleService.writeArticle();

        articleService.listArticle();

        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());

        assertThat(out.toString())
                .contains("번호 | 제목       | 등록일")
                .contains("1    | 자바 공부  | %s".formatted(now))
                .contains("2    | 자바 공부  | %s".formatted(now));
    }

    @Test
    @DisplayName("게시글 상세 보기")
    void t5() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                """;

        ArticleService articleService = new ArticleService(new Scanner(input));
        articleService.writeArticle();
        articleService.writeArticle();
        articleService.showDetails(1);
        articleService.showDetails(2);

        assertThat(out.toString())
                .contains("자바 텍스트 게시판 만들기")
                .contains("C++ 학습은 정말 유용하다.");
    }

    @Test
    @DisplayName("게시글 수정")
    void t6() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                자바 공부 수정
                자바 텍스트 수정 게시판 만들기
                """;

        ArticleService articleService = new ArticleService(new Scanner(input));
        articleService.writeArticle();
        articleService.writeArticle();
        articleService.updateArticle(1);
        articleService.listArticle();

        assertThat(out.toString())
                .contains("자바 공부 수정")
                .contains("자바 텍스트 수정 게시판 만들기");
    }

    @Test
    @DisplayName("게시글 삭제")
    void t7() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                """;

        ArticleService articleService = new ArticleService(new Scanner(input));
        articleService.writeArticle();
        articleService.writeArticle();
        articleService.deleteArticle(1);
        articleService.listArticle();

        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());

        assertThat(out.toString())
                .contains("2    | 자바 공부  | %s".formatted(now))
                .doesNotContain("1    | 자바 공부  | %s".formatted(now));
    }
}
