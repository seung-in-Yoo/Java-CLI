package com.ll.article;

import com.ll.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class AppTest {
    @Test
    @DisplayName("exit 입력은 true를 리턴하여 테스트 종료하도록 설정")
    void t1() {
        App app = new App();

        boolean result = app.handleCommand("exit");

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("exit가 아닌 입력은 false를 리턴")
    void t2() {
        App app = new App();

        boolean result = app.handleCommand("write");

        assertThat(result).isFalse();
    }
}
