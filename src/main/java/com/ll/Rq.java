package com.ll;

public class Rq {
    private final String cmd;
    private int id = 0;

    public Rq(String input) {
        String[] inputs  = input.split(" ",2);
        cmd = inputs[0];
        try {
            if (inputs.length>1) id = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            System.out.println("id 값은 숫자만 가능합니다.");
        }

    }

    public String getCmd() {
        return cmd;
    }

    public int getId(int defaultValue) {
        return id==0 ? defaultValue : id;
    }
}
