package com.ll;

public class Rq {
    private String actionName;
    private int actionId;

    public Rq(String cmd) {
        String[] cmdBits = cmd.split(" ", 2);
        actionName = cmdBits[0];

        if (cmdBits.length == 2) {
            try {
                actionId = Integer.parseInt(cmdBits[1].trim());
            } catch (NumberFormatException e) {
                this.actionId = -1;
            }
        } else {
            this.actionId = -1;
        }
    }

    public String getActionName() {
        return actionName;
    }

    public int getActionId() {
        return actionId;
    }
}
