package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    @DisplayName("== 게시판 앱 만들기 ==")
    public void t1() {
        String rs = AppTestRunner.run("");

        assertThat(rs).contains("== 게시판 앱 만들기 ==");
    }
}
