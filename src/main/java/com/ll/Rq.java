package com.ll;

import com.ll.article.ArticleController;
import com.ll.user.User;
import com.ll.user.UserController;

import java.util.Scanner;

public class Rq {
    // TODO: 사용자 입력 유틸 구현
    Scanner scanner = new Scanner(System.in);
    public Rq (Scanner scanner) {
        this.scanner = scanner;
    }
    ArticleController articleController = new ArticleController(scanner);

    public void run(String command) {
        if (command.equals("write")) {
            articleController.write();
        }
        else if (command.equals("list")) {
            articleController.list();
        }
        else if (command.startsWith("list")) {
            String sort = parseId(command);
            if (sort != null) {
                articleController.list(sort);
            } else articleController.list();
        }
        else if (command.startsWith("delete")) {
            String cmd = parseId(command);
            if (cmd != null)
                articleController.delete(Integer.parseInt(cmd));
            else System.out.println("잘못된 명령어입니다.");
        }
        else if (command.startsWith("update")) {
            String cmd = parseId(command);
            if (cmd != null)
                articleController.update(Integer.parseInt(cmd));
            else System.out.println("잘못된 명령어입니다.");
        }
        else if (command.startsWith("detail")) {
            String cmd = parseId(command);
            if (cmd != null)
                articleController.detail(Integer.parseInt(cmd));
            else System.out.println("잘못된 명령어입니다.");
        }
        else if (command.startsWith("search")) {
            String id = parseId(command);
            if (id != null)
                articleController.search(id);
            else System.out.println("잘못된 명령어입니다.");
        }
    }

    public String parseId(String command) {
        String[] parsed = command.split(" ");
        if (parsed.length != 2) return null;
        else return (parsed[1]);
    }

    public void run_notLogin(Scanner sc) {
        boolean isLogin = false;
        UserController userController = new UserController(scanner);
        while (!isLogin) {
            System.out.print("명령어 (signup / login) : ");
            String command = sc.nextLine();
            if (command.equals("signup")) {
                System.out.print("아이디 : ");
                String name = sc.nextLine();
                System.out.print("비밀번호 : ");
                String password = sc.nextLine();
                User newUser = new User(name, password);
                if (userController.addUser(newUser)) {
                    System.out.println("회원가입을 축하드립니다!");
                } else {
                    System.out.println("이미 존재하는 이름입니다.");
                }
            } else if (command.equals("login")) {
                System.out.print("아이디 : ");
                String name = sc.nextLine();
                System.out.print("비밀번호 : ");
                String password = sc.nextLine();
                if (userController.loginUser(new User(name, password))) {
                    System.out.println("로그인 되었습니다.");
                    isLogin = true;
                } else {
                    System.out.println("아이디나 비밀번호가 올바르지 않습니다.");
                }
            }
        }
    }
}
