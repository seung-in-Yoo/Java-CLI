package com.ll.comment;

import java.util.Scanner;

public class CommentController {
    Scanner scanner = new Scanner(System.in);
    public CommentController(Scanner scanner) { this.scanner = scanner; }
    CommentService commentService = new CommentService();

}
