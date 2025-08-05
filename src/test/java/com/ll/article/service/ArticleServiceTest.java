package com.ll.article.service;

import com.ll.article.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArticleServiceTest {
    @Test
    @DisplayName("게시글을 작성하면 Article 객체가 생성")
    void t1() {
        ArticleService service = new ArticleService();

        Article article = service.write("제목1", "내용1");

        assertThat(article).isNotNull();
        assertThat(article.getId()).isEqualTo(1);
        assertThat(article.getTitle()).isEqualTo("제목1");
        assertThat(article.getContent()).isEqualTo("내용1");
        assertThat(article.getRegDate()).isNotNull();
    }
}
