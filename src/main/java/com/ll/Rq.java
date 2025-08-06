package com.ll;

public class Rq {
    private final String cmd;
    private String keyword = null;

    public Rq(String input) {
        String[] inputs  = input.split(" ",2);
        cmd = inputs[0];
        if (inputs.length>1) keyword = inputs[1];
    }

    boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getCmd() {
        return cmd;
    }

    public int getId(int defaultValue) {
        if (isInteger(keyword)) return keyword==null ? defaultValue : Integer.parseInt(keyword);
        return defaultValue;
    }

    public String getKeyword(String defaultValue) {
        return keyword==null ? defaultValue : keyword;
    }
}
