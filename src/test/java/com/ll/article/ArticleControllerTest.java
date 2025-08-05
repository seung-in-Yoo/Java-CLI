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
}
