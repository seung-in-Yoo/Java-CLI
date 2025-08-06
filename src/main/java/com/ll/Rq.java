package com.ll;

import lombok.Getter;

@Getter
public class Rq {
    private final String actionName;
    private final int actionId;

    public Rq(String cmd) {
        String[] input = cmd.split(" ", 2);

        actionName = input[0];
        actionId = input.length == 2 ? Integer.parseInt(input[1]) : -1;
    }
}
