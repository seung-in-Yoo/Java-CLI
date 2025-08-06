package com.ll;


public class Rq {
    private final String action;
    private final String arg;

    public Rq(String cmd) {
        String[] parts = cmd.split(" ", 2);
        this.action = parts[0];
        this.arg = parts.length > 1 ? parts[1] : "";
    }

    public String getAction() {
        return action;
    }

    public String getArg() {
        return arg;
    }
    public int getIntArg(int defaultValue) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean is(String actionName) {
        return action.equals(actionName);
    }
}
