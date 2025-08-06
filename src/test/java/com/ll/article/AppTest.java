import com.ll.App;
import com.ll.article.Article;
import org.junit.jupiter.api.Test;


public class AppTest {

    @Test
    void t1() {
        App app = new App();  // App이 게시글을 등록하는 기능을 가지고 있다고 가정

        app.write("제목1", "내용1", "2025-08-06");

        assertEquals(1, app.articles.size());   // 1개 등록됐는지
        Article article = app.articles.get(0);

        assertEquals("제목1", article.title);
        assertEquals("내용1", article.content);
        assertEquals("2025-08-06", article.regDate);
        assertEquals(1, article.id);
    }
}
