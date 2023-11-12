package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Event {


    private final int FRIDAY = 5;
    private final int SATURDAY = 6;
    private final int SUNDAY = 7;
    private final int CHRISTMAS = 25;

    private int dayOfWeekNumber;
    private final int YEAR = 2023;
    private final int MONTH = 12;
    private final int DAY;
    private int discount;
    private int gift;

    public Event(int day) {
        DAY = day;
        LocalDate date = LocalDate.of(YEAR, MONTH, DAY);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        //월~일 : 1~7
        dayOfWeekNumber = dayOfWeek.getValue();
    }

    public void applyAll(Order order) {
        final int MIN_VALUE = 10000;
        final int NO_DISCOUNT = 0;

        if (order.sumAmount() < MIN_VALUE) {
            return;
        }
        discount += discountD_DAY();
        discount += discountWeekday(order.getDessertCount());
        discount += discountWeekend(order.getMainCount());
        discount += discountSpecial();
        discount += getGiftPrice(presentGift(order.sumAmount()));
    }

    public int discountD_DAY() {
        int max = 3400;
        int dDay = 25;
        int discountPerDay = 100;

        if (DAY > dDay) {
            return 0;
        }

        int dDayDiscount = max - (dDay - DAY) * discountPerDay;
        return dDayDiscount;
    }

    public int discountWeekday(int dessertCount) {
        if (dayOfWeekNumber != FRIDAY && dayOfWeekNumber != SATURDAY) {
            return YEAR * dessertCount;
        }
        return 0;
    }

    public int discountWeekend(int mainCount) {
        if (dayOfWeekNumber == FRIDAY || dayOfWeekNumber == SATURDAY) {
            return YEAR * mainCount;
        }
        return 0;
    }

    public int discountSpecial() {
        final int SPECIAL_DISCOUNT = 1000;
        if (dayOfWeekNumber == SUNDAY || dayOfWeekNumber == CHRISTMAS) {
            return SPECIAL_DISCOUNT;
        }
        return 0;
    }

    public String presentGift(int amount) {
        final String CHAMPAGNE = "샴페인";
        final String NO_GIFT = "없음";
        final int MIN_FOR_CHAMPAGNE = 120000;
        if (amount >= MIN_FOR_CHAMPAGNE) {
            return CHAMPAGNE;
        }
        return NO_GIFT;
    }

    public String presentBadge(int discountAmount) {
        if (discountAmount >= Badge.산타.getPrice()) {
            return Badge.산타.name();
        }
        if (discountAmount >= Badge.트리.getPrice()) {
            return Badge.트리.name();
        }
        if (discountAmount >= Badge.스타.getPrice()) {
            return Badge.스타.name();
        }
        return Badge.없음.name();
    }

    public int getGiftPrice(String gift) {
        return GiftPrice.valueOf(gift).getPrice();
    }

    public int getDiscountAmount() {
        return discount;
    }
}
