package com.ll.article;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
// TODO: ArticleRepository 관련 TDD 테스트 작성
public class ArticleRepositoryTest {
    @Test
    @DisplayName("게시물 저장 기능 테스트")
    void t1() {
        ArticleRepository articleRepository = new ArticleRepository();
        String title = "test title 1";
        String content = "test content 1";
        Article article = new Article(1, title, content);
        assertThat(articleRepository.save(title, content)).isEqualTo(article);
    }

    @Test
    @DisplayName("ID로 게시물 찾기 기능 테스트")
    void t2() {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save("test title 1", "test content 1");
        articleRepository.save("test title 2", "test content 2");
        articleRepository.save("test title 3", "test content 3");
        Article article = new Article(2, "test title 2", "test content 2");
        int searchId = 2;
        assertThat(articleRepository.findById(searchId)).isEqualTo(article);
    }

    @Test
    @DisplayName("ID로 게시물 찾기 기능 테스트 (없는 번호)")
    void t3() {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save("test title 1", "test content 1");
        articleRepository.save("test title 2", "test content 2");
        articleRepository.save("test title 3", "test content 3");
        int searchId = 4;
        assertThat(articleRepository.findById(searchId)).isEqualTo(null);
    }

    @Test
    @DisplayName("모든 게시물 찾기")
    void t4() {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save("test title 1", "test content 1");
        articleRepository.save("test title 2", "test content 2");
        articleRepository.save("test title 3", "test content 3");

        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "test title 1", "test content 1"));
        articles.add(new Article(2, "test title 2", "test content 2"));
        articles.add(new Article(3, "test title 3", "test content 3"));
        assertThat(articleRepository.findAll()).isEqualTo(articles);
    }

    @Test
    @DisplayName("Id를 통해 게시물 지우기")
    void t5() {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save("test title 1", "test content 1");
        articleRepository.save("test title 2", "test content 2");
        articleRepository.save("test title 3", "test content 3");

        articleRepository.deleteById(2);

        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "test title 1", "test content 1"));
        articles.add(new Article(3, "test title 3", "test content 3"));

        assertThat(articleRepository.findAll()).isEqualTo(articles);
    }

    @Test
    @DisplayName("Id를 통해 게시물 지우기 return 확인")
    void t6() {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save("test title 1", "test content 1");
        articleRepository.save("test title 2", "test content 2");
        articleRepository.save("test title 3", "test content 3");

        assertThat(false).isEqualTo(articleRepository.deleteById(4));
    }


    @Test
    @DisplayName("Id를 통해 게시물 지우기 (실패)")
    void t7() {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.save("test title 1", "test content 1");
        articleRepository.save("test title 2", "test content 2");
        articleRepository.save("test title 3", "test content 3");

        articleRepository.deleteById(4);

        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "test title 1", "test content 1"));
        articles.add(new Article(2, "test title 2", "test content 2"));
        articles.add(new Article(3, "test title 3", "test content 3"));

        assertThat(articleRepository.findAll()).isEqualTo(articles);
    }
}
