package christmas.control;

import christmas.model.FoodType;

public class Validation {
    public static void validDate(String input) {
        final int START_DAY = 1;
        final int END_DAY = 31;
        int date;

        try {
            date = Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] : 숫자 형식으로 입력해 주세요. ");
        }

        if (date < START_DAY || date > END_DAY) {
            throw new IllegalArgumentException("[ERROR] : 이벤트 시작일과 종료일을 확인해 주세요.");
        }
    }

    public static void validItem(String input) {
        int max = 0;
        try {
            String[] splitItems = input.split(",");
            for (String item : splitItems) {
                String food = item.substring(0, item.indexOf("-"));
                int count = Integer.parseInt(item.substring(item.indexOf("-") + 1));
                max += count;

                validFood(food);
                validCount(count, max);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException("[ERROR] : 잘못된 입력입니다. ");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] : " + e.getMessage());
        }
    }

    public static void validFood(String food) {
        try {
            FoodType.valueOf(food).getType();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] : 잘못된 음식 이름이 포함되어 있습니다.");
        }
    }

    public static void validCount(int count, int max) {
        final int MIN_COUNT = 1;
        final int MAX_COUNT = 20;
        if (count < MIN_COUNT || count > MAX_COUNT) {
            throw new IllegalArgumentException("각 항목별 수량이 1~20개인지 확인해 주세요.");
        }
        if (max > MAX_COUNT) {
            throw new IllegalArgumentException("총 수량은 20개 까지만 허용됩니다.");
        }
    }
}
