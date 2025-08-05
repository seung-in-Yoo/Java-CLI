package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    @Test
    @DisplayName("전체 실행 테스트")
    void t1() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        String inputs = """
                write
                자바 공부
                자바 텍스트 게시판 만들기
                list
                detail 1
                update 1
                Java 게시판
                콘솔 기반으로 구현
                delete 1
                exit
                """;

        new App().run(new Scanner(inputs));

        assertThat(out.toString())
                .contains("=> 게시글이 등록되었습니다.")
                .contains("1    | 자바 공부  | 2025-08-05")
                .contains("""
                        번호: 1
                        제목: 자바 공부
                        내용: 자바 텍스트 게시판 만들기
                        등록일: 2025-08-05
                        """)
                .contains("=> 게시글이 수정되었습니다.")
                .contains("=> 게시글이 삭제되었습니다.")
                .contains("프로그램을 종료합니다.");

    }
}
