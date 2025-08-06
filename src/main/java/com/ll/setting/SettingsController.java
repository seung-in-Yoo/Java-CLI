package com.ll.setting;

// setting order id asc
// setting page 5

import com.ll.Rq;

public class SettingsController {
    public void actionSetting(Rq rq) {
        String[] options = rq.getKeyword("0").split(" ", 3);
        if (options.length < 2) {
            System.out.println("=> 설정할 옵션과 옵션값을 함께 입력해주세요.");
            return ;
        }

        String settingOrder = options[0].trim();
        String secondOrder = options[1].trim();
        String thirdOrder = "";
        if (options.length>2) thirdOrder = options[2].trim();

        switch(settingOrder) {
            case "order" -> {
                if (secondOrder.equals("id") || secondOrder.equals("reg")) {
                    if (thirdOrder.equals("asc")) SettingsContext.isAsc = true;
                    else if (thirdOrder.equals("desc")) SettingsContext.isAsc = false;
                    else {
                        System.out.println("=> 정렬 순서는 (asc/desc)만 가능합니다.");
                        return ;
                    }
                    SettingsContext.orderName = secondOrder;
                    System.out.printf("=> 정렬 기준이 %s %s 으로 바뀌었습니다.\n", secondOrder, SettingsContext.isAsc ? "오름차순" : "내림차순");
                } else System.out.println("=> 정렬 기준은 id나 reg만 가능합니다.");
            }
            case "page" -> {
                try {
                    SettingsContext.pageSize = Integer.parseInt(secondOrder);
                    System.out.printf("=> 페이지 크기가 %d 로 설정되었습니다.\n", SettingsContext.pageSize);
                } catch (NumberFormatException e) {
                    System.out.println("=> 페이지 크기는 자연수만 가능합니다.");
                }

            }
            default -> System.out.println("=> 존재하지 않는 옵션명입니다.");
        }
    }
}
