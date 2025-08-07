package com.ll;

import com.ll.service.ArticleService;

import java.util.Scanner;

public class App {
    private final ArticleService service = new ArticleService();
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("== 게시판 앱 만들기 ==");
        while (true) {
            System.out.print("명령어: ");
            String cmd = sc.nextLine().trim();
            Rq rq = new Rq(cmd);
            String action = rq.getAction();
            String arg = rq.getArg();

            if (action.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            switch (action) {
                case "write":
                    doWrite();
                    break;
                case "list":
                    System.out.print(service.list());
                    break;
                case "detail":
                    if (arg.isBlank()) {
                        System.out.println("사용법: detail id");
                    } else {
                        try {
                            System.out.print(service.detail(Integer.parseInt(arg)));
                        } catch (NumberFormatException e) {
                            System.out.println("ID는 숫자로 입력해주세요.");
                        }
                    }
                    break;
                case "update":
                    if (arg.isBlank()) {
                        System.out.println("사용법: update id");
                    } else {
                        doUpdate(arg);
                    }
                    break;
                case "delete":
                    if (arg.isBlank()) {
                        System.out.println("사용법: delete id");
                    } else {
                        doDelete(arg);
                    }
                    break;
                default:
                    System.out.println("알 수 없는 명령어입니다.");
            }
        }
    }

    private void doWrite() {;
        System.out.print("제목: ");
        String title = sc.nextLine().trim();
        System.out.print("내용: ");
        String content = sc.nextLine().trim();
        service.write(title, content);
        System.out.println("게시글이 등록되었습니다.");
    }

    private void doUpdate(String arg) {
        try {
            int id = Integer.parseInt(arg);
            System.out.printf("제목 (현재: %s): ", service.detailTitle(id)); // service.detailTitle 추가 필요
            String nt = sc.nextLine().trim();
            System.out.printf("내용 (현재: %s): ", service.detailContent(id)); // service.detailContent 추가 필요
            String nc = sc.nextLine().trim();

            if (service.update(id, nt, nc)) {
                System.out.println("게시글이 수정되었습니다.");
            } else {
                System.out.println(id + "번 게시글은 존재하지 않습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID는 숫자로 입력해주세요.");
        }
    }

    private void doDelete(String arg) {
        try {
            int id = Integer.parseInt(arg);
            if (service.delete(id)) {
                System.out.println("게시글이 삭제되었습니다.");
            } else {
                System.out.println(id + "번 게시글은 존재하지 않습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID는 숫자로 입력해주세요.");
        }
    }

    public static void main(String[] args) {
        new App().run();
    }
}