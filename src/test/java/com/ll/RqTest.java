package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RqTest {
    @Test
    @DisplayName("회원가입 후 로그인까지 정상 수행")
    void test_run_notLogin() {
        // signup -> testuser / testpass -> login -> testuser / testpass
        String input = String.join(System.lineSeparator(),
                "signup",
                "testuser",
                "testpass",
                "login",
                "testuser",
                "testpass"
        );

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner sc = new Scanner(in);

        Rq rq = new Rq(sc);
        rq.run_notLogin(sc); // 내부적으로 while문이 종료되어야 하므로 로그인 성공이 되어야 함
    }
}
