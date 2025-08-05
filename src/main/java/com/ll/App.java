package com.ll;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 게시판 앱 만들기 ==");
        while (true) {
            System.out.print("명령어: ");
            String command = sc.nextLine();
            if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else if (command.equals("write")) {

            }
            else if (command.equals("list")) {

            }
            else if (command.startsWith("delete")) {

            }
            else if (command.startsWith("update")) {

            }
            else if (command.startsWith("detail")) {

            }
        }
    }
}