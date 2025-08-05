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

        assertThat(rs).contains("게시글이 등록되었습니다.");
    }
}
