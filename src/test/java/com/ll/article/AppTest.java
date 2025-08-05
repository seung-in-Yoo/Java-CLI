package com.ll.article;

import com.ll.App;
import com.ll.article.controller.ArticleController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

public class AppTest {
    @Test
    @DisplayName("exit 입력은 true를 리턴하여 테스트 종료하도록 설정")
    void t1() {
        App app = new App();
        ArticleController controller = new ArticleController(new Scanner(System.in));

        boolean result = app.handleCommand("exit", controller);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("exit가 아닌 입력은 false를 리턴")
    void t2() {
        App app = new App();
        ArticleController controller = new ArticleController(new Scanner(System.in));

        boolean result = app.handleCommand("test", controller);

        assertThat(result).isFalse();
    }
}
