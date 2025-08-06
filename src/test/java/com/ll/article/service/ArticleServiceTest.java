package com.ll.article.service;

import com.ll.article.entity.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 해당 테스트 코드에서 assertThat 부분은 포함여부를 확인하는 .contains보다 단일 객체의 필드 검증인 .isEqualTo가 더 적절
public class ArticleServiceTest {
    @Test
    @DisplayName("게시글을 작성하면 Article 객체가 생성")
    void t1() {
        ArticleService service = new ArticleService();

        Article article = service.writeArticle("제목1", "내용1");

        assertThat(article).isNotNull();
        assertThat(article.getId()).isEqualTo(1);
        assertThat(article.getTitle()).isEqualTo("제목1");
        assertThat(article.getContent()).isEqualTo("내용1");
        assertThat(article.getRegDate()).isNotNull();
    }

    @Test
    @DisplayName("작성된 게시글들을 최신순으로 조회")
    void t2() {
        ArticleService service = new ArticleService();

        service.writeArticle("제목1", "내용1");
        service.writeArticle("제목2", "내용2");

        List<Article> articles = service.listArticles();

        assertThat(articles).hasSize(2);
        assertThat(articles.get(0).getTitle()).isEqualTo("제목2");
        assertThat(articles.get(1).getTitle()).isEqualTo("제목1");
    }

    @Test
    @DisplayName("게시글 ID를 통해 게시글 조회")
    void t3() {
        ArticleService service = new ArticleService();

        Article article1 = service.writeArticle("제목1", "내용1");
        Article article2 = service.writeArticle("제목2", "내용2");

        Article found = service.findArticleById(article1.getId());

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(article1.getId());
        assertThat(found.getTitle()).isEqualTo("제목1");
        assertThat(found.getContent()).isEqualTo("내용1");
    }

    @Test
    @DisplayName("게시글 ID를 통해 기존 게시글 수정")
    void t4() {
        ArticleService service = new ArticleService();

        Article article1 = service.writeArticle("제목1", "내용1");

        service.updateArticle(article1.getId(), "제목수정1", "내용수정1");

        Article updated = service.findArticleById(article1.getId());

        assertThat(updated).isNotNull();
        assertThat(updated.getTitle()).isEqualTo("제목수정1");
        assertThat(updated.getContent()).isEqualTo("내용수정1");
    }

    @Test
    @DisplayName("게시글 ID를 통해 게시글 삭제")
    void t5() {
        ArticleService service = new ArticleService();
        Article article1 = service.writeArticle("제목1", "내용1");
        Article article2 = service.writeArticle("제목2", "내용2");

        service.deleteArticle(article1.getId());

        List<Article> articles = service.listArticles();

        assertThat(articles).hasSize(1);
        assertThat(articles.get(0).getId()).isEqualTo(article2.getId());
    }

    @Test
    @DisplayName("게시글을 조회하면 조회수가 1 증가")
    void t6() {
        ArticleService service = new ArticleService();

        Article article = service.writeArticle("제목1", "내용1");

        assertThat(article.getViewCount()).isEqualTo(0);
        service.increaseViewCount(article);
        assertThat(article.getViewCount()).isEqualTo(1);
        service.increaseViewCount(article);
        assertThat(article.getViewCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("검색 키워드로 제목 또는 내용을 포함하는 게시글 조회")
    void t7() {
        ArticleService service = new ArticleService();

        service.writeArticle("자바", "자바 CLI 게시판 만들기");
        service.writeArticle("스프링 공부", "스프링 프로젝트 진행하기");
        service.writeArticle("리액트", "리액트 투두 앱 만들기");
        service.writeArticle("자바 중급", "자바 중급 강의 듣기");

        List<Article> results = service.searchArticles("자바");

        assertThat(results).hasSize(2);
        // 검색은 정확히 값이 일치해야 하는게 아니고 키워드만 포함되면 되니깐 .contains 사용
        assertThat(results.get(0).getTitle()).contains("자바");
        assertThat(results.get(1).getTitle()).contains("자바");
    }
}
