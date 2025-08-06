package com.ll.article;

import com.ll.Rq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArticleControllerTest {
    @Test
    @DisplayName("게시글 작성")
    void t1() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                """;

        ArticleController articleController = new ArticleController(new Scanner(input));
        articleController.actionWrite();

        assertThat(out.toString()).contains("=> 게시글이 등록되었습니다.");
    }

    @Test
    @DisplayName("게시글 목록 보기")
    void t2() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                """;

        ArticleController articleController = new ArticleController(new Scanner(input));
        articleController.actionWrite();
        articleController.actionWrite();

        articleController.actionList();

        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());

        assertThat(out.toString())
                .contains("번호 | 제목(조회수)       | 등록일")
                .contains("2    | C++ 공부 (0)  | %s".formatted(now))
                .contains("1    | 자바 공부 (0)  | %s".formatted(now));

    }

    @Test
    @DisplayName("게시글 목록 보기 - 조회수 증가")
    void t2_1() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                """;

        ArticleController articleController = new ArticleController(new Scanner(input));
        articleController.actionWrite();
        articleController.actionWrite();

        Rq rq1 = new Rq("detail 1");
        Rq rq2 = new Rq("detail 2");

        articleController.actionDetail(rq1);
        articleController.actionDetail(rq1);
        articleController.actionDetail(rq2);
        articleController.actionList();

        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());

        assertThat(out.toString())
                .contains("번호 | 제목(조회수)       | 등록일")
                .contains("2    | C++ 공부 (1)  | %s".formatted(now))
                .contains("1    | 자바 공부 (2)  | %s".formatted(now));

    }

    @Test
    @DisplayName("게시글 상세 보기")
    void t3() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                """;

        Rq rq1 = new Rq("detail 1");
        Rq rq2 = new Rq("detail 2");

        ArticleController articleController = new ArticleController(new Scanner(input));
        articleController.actionWrite();
        articleController.actionWrite();
        articleController.actionDetail(rq1);
        articleController.actionDetail(rq2);

        assertThat(out.toString())
                .contains("자바 텍스트 게시판 만들기")
                .contains("C++ 학습은 정말 유용하다.");
    }

    @Test
    @DisplayName("게시글 수정")
    void t4() {
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

        Rq rq1 = new Rq("detail 1");

        ArticleController articleController = new ArticleController(new Scanner(input));
        articleController.actionWrite();
        articleController.actionWrite();
        articleController.actionModify(rq1);
        articleController.actionDetail(rq1);

        assertThat(out.toString())
                .contains("자바 공부 수정")
                .contains("자바 텍스트 수정 게시판 만들기");
    }

    @Test
    @DisplayName("게시글 삭제")
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

        Rq rq1 = new Rq("delete 1");

        ArticleController articleController = new ArticleController(new Scanner(input));
        articleController.actionWrite();
        articleController.actionWrite();
        articleController.actionDelete(rq1);
        articleController.actionList();

        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());

        assertThat(out.toString())
                .contains("2    | C++ 공부 (0)  | %s".formatted(now))
                .doesNotContain("1    | 자바 공부 (0)  | %s".formatted(now));
    }

    @Test
    @DisplayName("게시글 검색")
    void t6() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                JS 공부
                JS 학습도 정말 유용하다.
                """;

        Rq rq1 = new Rq("search 정말");

        ArticleController articleController = new ArticleController(new Scanner(input));
        articleController.actionWrite();
        articleController.actionWrite();
        articleController.actionWrite();

        articleController.actionSearch(rq1);

        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());

        assertThat(out.toString())
                .contains("3    | JS 공부 (0)  | %s".formatted(now))
                .contains("2    | C++ 공부 (0)  | %s".formatted(now))
                .contains("=> 검색 결과 : 2 건")
                .doesNotContain("1    | 자바 공부 (0)  | %s".formatted(now));
    }

    @Test
    @DisplayName("게시글 검색 - 검색 결과 없음")
    void t7() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream defaultPrint = System.out;
        System.setOut(new PrintStream(out));

        String input = """
                자바 공부
                자바 텍스트 게시판 만들기
                C++ 공부
                C++ 학습은 정말 유용하다.
                JS 공부
                JS 학습도 정말 유용하다.
                """;

        Rq rq1 = new Rq("search 검색결과없음");

        ArticleController articleController = new ArticleController(new Scanner(input));
        articleController.actionWrite();
        articleController.actionWrite();
        articleController.actionWrite();

        articleController.actionSearch(rq1);

        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());

        assertThat(out.toString())
                .contains("-----------------------------")
                .contains("=> 검색 결과 : 0 건")
                .doesNotContain("3    | JS 공부 (0)  | %s".formatted(now))
                .doesNotContain("2    | C++ 공부 (0)  | %s".formatted(now))
                .doesNotContain("1    | 자바 공부 (0)  | %s".formatted(now));
    }
}
