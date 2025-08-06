package com.ll.article.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class Article {
    private final int id;
    private final LocalDate regDate;
    @Setter // 읽기 전용인 필드는 수정하지 못하도록 명시적으로 수정하는곳만 setter 처리
    private String title;
    @Setter // 읽기 전용인 필드는 수정하지 못하도록 명시적으로 수정하는곳만 setter 처리
    private String content;
    private int viewCount;

    // 새로 생성할 때 사용
    private Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = LocalDate.now();; // 정적 팩토리 메서드에서 날짜 생성하도록 리팩토링
        this.viewCount = 0; // 조회수는 초기값 0으로
    }

    // 파일 불러올때 사용 (오버로딩을 통해 파라미터 수 다시 맞춰줌)
    private Article(int id, String title, String content, LocalDate regDate, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.viewCount = viewCount;
    }

    // 새로운 글 생성할 때 사용
    public static Article of(int id, String title, String content) {
        return new Article(id, title, content);
    }

    // 파일 불러올때 사용
    public static Article ofFile(int id, String title, String content, LocalDate regDate, int viewCount) {
        return new Article(id, title, content, regDate, viewCount);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseViewCount() { viewCount++; }
}