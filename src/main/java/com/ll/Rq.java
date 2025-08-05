package com.ll;

import lombok.Getter;

/*
Rq의 기능
1. actionName 분리
2. actionName 뒤의 id 확인
 */

@Getter
public class Rq {
    private final String actioName;
    private final int actionId;

    public Rq(String cmd) {
        String[] input = cmd.split(" ", 2);

        actioName = input[0].trim();
        actionId = Integer.parseInt(input[1].trim());
    }
}
