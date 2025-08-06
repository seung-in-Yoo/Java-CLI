package com.ll.article;

import com.ll.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleControllerTest {

    @Test
    @DisplayName("write")
    public void t1() {
        String rs = AppTestRunner.run("""
                write
                자바 공부
                자바 텍스트 게시판 만들기
                """);

        assertThat(rs).contains("1번 게시글이 등록되었습니다.");
    }

    @Test
    @DisplayName("list")
    public void t2() {
        String rs = AppTestRunner.run("""
                write
                자바 공부
                자바 텍스트 게시판 만들기
                write
                spring 공부
                spring 게시판 만들기
                list
                """);

        assertThat(rs)
                .contains("1번 게시글이 등록되었습니다.")
                .contains("2번 게시글이 등록되었습니다.")
                .contains(" 번호 | 제목 | 등록일 ")
                .contains("1 | 자바 공부 | 2025-08-06")
                .contains("2 | spring 공부 | 2025-08-06");
    }

    @Test
    @DisplayName("detail")
    public void t3() {
        String rs = AppTestRunner.run("""
                write
                자바 공부
                자바 텍스트 게시판 만들기
                write
                spring 공부
                spring 게시판 만들기
                list
                detail 1
                """);

        assertThat(rs)
                .contains("번호 : 1")
                .contains("제목 : 자바 공부")
                .contains("내용 : 자바 텍스트 게시판 만들기")
                .contains("등록일 : 2025-08-06");
    }

    @Test
    @DisplayName("update")
    public void t4() {
        String rs = AppTestRunner.run("""
                write
                자바 공부
                자바 텍스트 게시판 만들기
                detail 1
                update 1
                Java 게시판
                콘솔 기반으로 구현
                detail 1
                """);

        assertThat(rs)
                .contains("제목 : 자바 공부")
                .contains("내용 : 자바 텍스트 게시판 만들기")
                .contains("제목 : Java 게시판")
                .contains("내용 : 콘솔 기반으로 구현");
    }

    @Test
    @DisplayName("delete")
    public void t5() {
        String rs = AppTestRunner.run("""
                write
                자바 공부
                자바 텍스트 게시판 만들기
                write
                spring 공부
                spring 게시판 만들기
                delete 1
                list
                """);

        assertThat(rs)
                .contains("1번 게시글이 삭제되었습니다.")
                .contains("2 | spring 공부 | 2025-08-06")
                .doesNotContain("1 | 자바 공부 | 2025-08-06");
    }
}
