package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RqTest {
    @Test
    @DisplayName("Rq cmd 추출 1")
    void t1() {
        Rq rq = new Rq("list");
        
        assertThat(rq.getCmd()).isEqualTo("list");
    }

    @Test
    @DisplayName("Rq cmd 추출 2")
    void t2() {
        Rq rq = new Rq("detail 1");

        assertThat(rq.getCmd()).isEqualTo("detail");
    }

    @Test
    @DisplayName("Rq id 추출 1")
    void t3() {
        Rq rq = new Rq("detail 1");

        assertThat(rq.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Rq id 추출 2 - id값 없음")
    void t4() {
        Rq rq = new Rq("detail");

        assertThat(rq.getId()).isEqualTo(0);
    }
}
