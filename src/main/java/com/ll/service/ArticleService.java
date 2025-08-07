package com.ll.service;


import com.ll.article.Article;

import java.time.LocalDate;
import java.util.List;

public class ArticleService {
    private final com.ll.repository.ArticleRepository repo = new com.ll.repository.ArticleRepository();

    // 글 쓰기
    public Article write(String title, String content) {
        String regDate = LocalDate.now().toString();
        return repo.save(title, content, regDate);
    }

    // 글 목록
    public String list() {
        List<Article> all = repo.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("번호 | 제목 | 등록일\n")
                .append("-------------------\n");
        for (int i = all.size() - 1; i >= 0; i--) {
            Article a = all.get(i);
            sb.append(String.format("%d | %s | %s\n",
                    a.getId(), a.getTitle(), a.getRegDate()));
        }
        return sb.toString();
    }

    // 상세 보기
    public String detail(int id) {
        Article a = repo.findById(id);
        if (a == null) {
            return id + "번 게시글은 존재하지 않습니다.\n";
        }
        return String.format(
                "번호: %d\n제목: %s\n내용: %s\n등록일: %s\n",
                a.getId(), a.getTitle(), a.getContent(), a.getRegDate()
        );
    }
    public String detailTitle(int id) {
        Article a = repo.findById(id);
        return a != null ? a.getTitle() : "";
    }
    public String detailContent(int id) {
        Article a = repo.findById(id);
        return a != null ? a.getContent() : "";
    }
    // 수정
    public boolean update(int id, String newTitle, String newContent) {
        Article a = repo.findById(id);
        if (a == null) return false;
        a.setTitle(newTitle);
        a.setContent(newContent);
        return true;
    }

    //삭제
    public boolean delete(int id) {
        return repo.delete(id);
    }
}