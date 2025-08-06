package com.ll.article;

import com.ll.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {

    @Test
    void t1() {
        App app = new App();  // App이 게시글을 등록하는 기능을 가지고 있다고 가정

        app.write("제목1", "내용1", "2025-08-06");

        assertEquals(1, app.getArticles().size());   // 1개 등록됐는지
        Article article = app.getArticles().get(0);

        assertEquals("제목1", article.getTitle());
        assertEquals("내용1", article.getContent());
        assertEquals("2025-08-06", article.getRegDate());
        assertEquals(1, article.getId());
    }
}
