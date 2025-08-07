package com.ll.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Comment {
    private int id;
    private String content;
    private LocalDate regDate;

    public Comment(int id, String content) {
        this.id = id;
        this.content = content;
        this.regDate = LocalDate.now();
    }
}
