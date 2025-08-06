package com.ll.setting;

import com.ll.Rq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SettingsControllerTest {
    @Test
    @DisplayName("정렬 설정 변경")
    void t1() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Rq rq = new Rq("setting order id asc");

        new SettingsController().actionSetting(rq);

        assertThat(baos.toString()).isEqualTo("=> 정렬 기준이 id 오름차순 으로 바뀌었습니다.\n");
    }

    @Test
    @DisplayName("정렬 설정 변경 - 첫번째 키워드 미입력")
    void t2() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Rq rq = new Rq("setting id asc");

        new SettingsController().actionSetting(rq);

        assertThat(baos.toString().trim()).isEqualTo("=> 존재하지 않는 옵션명입니다.");
    }

    @Test
    @DisplayName("정렬 설정 변경 - 두번째 키워드 미입력")
    void t3() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Rq rq = new Rq("setting order asc");

        new SettingsController().actionSetting(rq);

        assertThat(baos.toString().trim()).isEqualTo("=> 정렬 기준은 id나 reg만 가능합니다.");
    }

    @Test
    @DisplayName("정렬 설정 변경 - 세번째 키워드 미입력")
    void t4() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Rq rq = new Rq("setting order id");

        new SettingsController().actionSetting(rq);

        assertThat(baos.toString().trim()).isEqualTo("=> 정렬 순서는 (asc/desc)만 가능합니다.");
    }

    @Test
    @DisplayName("페이지 크기 설정 변경")
    void t5() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Rq rq = new Rq("setting page 5");

        new SettingsController().actionSetting(rq);

        assertThat(baos.toString().trim()).isEqualTo("=> 페이지 크기가 5 로 설정되었습니다.");
    }

    @Test
    @DisplayName("페이지 크기 미입력")
    void t6() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        Rq rq = new Rq("setting page");

        new SettingsController().actionSetting(rq);

        assertThat(baos.toString().trim()).isEqualTo("=> 설정할 옵션과 옵션값을 함께 입력해주세요.");
    }
}
