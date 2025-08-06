package com.ll.article.repository;

import com.ll.article.entity.Article;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();
    private int lastId = 0;

    public Article save(String title, String content) {
        Article article = Article.of(++lastId, title, content);
        articles.add(article);
        return article;
    }

    public List<Article> findAll() {
        return List.copyOf(articles); // copyOf 사용하여 원본 수정 불가능하도록 처리
    }

    public Article findById(int id) {
        return articles.stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null); // 찾지못하면 null
    }

    public void delete(Article article) {
        articles.remove(article);
    }

    public List<Article> findByKeyword(String keyword) {
        return articles.stream()
                .filter(article -> article.getTitle().contains(keyword) || article.getContent().contains(keyword))
                .collect(Collectors.toList());
    }

    public void saveFile(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Article article : articles) {
                // id|제목|내용|생성일|조회수 형태로 저장
                String line = String.format("%d|%s|%s|%s|%d",
                        article.getId(),
                        article.getTitle().replace("|", " "),
                        article.getContent().replace("|", " "),
                        article.getRegDate(),
                        article.getViewCount());
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public void loadFile(String fileName) throws IOException {
        articles.clear(); // 기존 데이터 클리어
        File file = new File(fileName);
        if (!file.exists()) { return; }

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|", 5); // id,제목,내용,생성일,조회수를 배열로 저장
                if (parts.length != 5) { continue; } // 데이터가 5개가 아니면 continue 처리
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String content = parts[2];
                LocalDate regDate = LocalDate.parse(parts[3]);
                int viewCount = Integer.parseInt(parts[4]);
                Article article = Article.ofFile(id, title, content, regDate, viewCount);
                articles.add(article);
                lastId = Math.max(lastId, id); // 마지막 게시글 번호 갱신
            }
        }
    }
}