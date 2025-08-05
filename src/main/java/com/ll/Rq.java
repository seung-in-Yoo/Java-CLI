package com.ll;

public class Rq {
    private final String actionName;
    private final int paramId;

    public Rq(String command) {
        String[] split = command.split(" ");

        this.actionName = split[0];

        int id = -1;

        // 두 번째 단어가 있다면 파싱해서 저장하고 없으면 -1 처리
        if (split.length > 1) {
            try {
                id = Integer.parseInt(split[1]);
            } catch (NumberFormatException e) {
                // 숫자로 바꿀 수 없으면 NumberFormatExeption 예외 처리
            }
        }
        this.paramId = id;
    }

    public String getActionName() {
        return actionName;
    }

    public int getParamId() {
        return paramId;
    }
}
