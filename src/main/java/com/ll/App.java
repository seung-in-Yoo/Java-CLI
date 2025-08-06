package com.ll;

import com.ll.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);
    private final List<Article> articles = new ArrayList<Article>();
    private int lastId = 0;
    public void run() {
        System.out.println("== 게시판 앱 만들기 ==");
        while (true) {
            System.out.print("명령어: ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            String[] parts = cmd.split(" ", 2);
            String action = parts[0];
            String arg    = parts.length > 1 ? parts[1] : "";

            if (cmd.equals("write")) {
                write();
            } else if (cmd.equals("list")) {
                list();
            } else if (cmd.startsWith("detail")) {
                if (arg.isEmpty()) {
                    System.out.println("사용법: detail id");
                } else {
                    try {
                        int id = Integer.parseInt(arg);
                        detail(id);
                    } catch (NumberFormatException e) {
                        System.out.println("ID는 숫자로 입력해주세요.");
                    }
                }
            } else if (cmd.startsWith("update")) {
                if (arg.isEmpty()) {
                    System.out.println("사용법: update id");
                } else {
                    try {
                        int id = Integer.parseInt(arg);
                        update(id);
                    } catch (NumberFormatException e) {
                        System.out.println("ID는 숫자로 입력해주세요.");
                    }
                }
            } else if (cmd.startsWith("delete")) {
                if (arg.isEmpty()) {
                    System.out.println("사용법: delete id");
                } else {
                    try {
                        int id = Integer.parseInt(arg);
                        delete(id);
                    } catch (NumberFormatException e) {
                        System.out.println("ID는 숫자로 입력해주세요.");
                    }
                }
            } else {
                System.out.println("알 수 없는 명령어입니다.");
            }
        }

    }
    public List<Article> getArticles() {
        return articles;
    }
    private void write() {
        System.out.print("제목: ");
        String title = sc.nextLine();
        System.out.print("내용: ");
        String content = sc.nextLine();
        String regDate = java.time.LocalDate.now().toString();


        write(title, content, regDate);
        System.out.printf("%d번 게시글이 등록되었습니다.%n", lastId);
    }

    public void write(String title, String content, String regDate) {
        Article article = new Article(++lastId, title, content, regDate);
        articles.add(article);

    }
    public String getListText() {
        StringBuilder sb = new StringBuilder();
        sb.append("번호 / 제목 / 작성일\n")
                .append("-------------------\n");
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article a = articles.get(i);
            sb.append(String.format("%d / %s / %s\n",
                    a.getId(), a.getTitle(), a.getRegDate()));
        }
        return sb.toString();
    }

    public void list() {
        System.out.print(getListText());
    }
    private void detail(int id) {
        for (Article a : articles) {
            if (a.getId() == id) {
                System.out.println("번호: " + a.getId());
                System.out.println("제목: " + a.getTitle());
                System.out.println("내용: " + a.getContent());
                System.out.println("등록일: " + a.getRegDate());
                return;
            }
        }
        System.out.println(id + "번 게시글은 존재하지 않습니다.");
    }
    private void update(int id) {
        for (Article a : articles) {
            if (a.getId() == id) {
                System.out.printf("제목 (현재: %s): ", a.getTitle());
                String newTitle = sc.nextLine();
                System.out.printf("내용 (현재: %s): ", a.getContent());
                String newContent = sc.nextLine();

                a.setTitle(newTitle);
                a.setContent(newContent);
                System.out.println("게시글이 수정되었습니다.");
                return;
            }
        }
        System.out.println(id + "번 게시글은 존재하지 않습니다.");
    }
    private void delete(int id) {
        boolean removed = articles.removeIf(a -> a.getId() == id);
        if (removed) {
            System.out.println("게시글이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 게시글은 존재하지 않습니다.");
        }
    }
}