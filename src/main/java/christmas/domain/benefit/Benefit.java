package christmas.domain.benefit;

import christmas.domain.category.Category;
import christmas.domain.date.Date;
import christmas.domain.order.Order;
import christmas.dto.BenefitDto;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Benefit {

    private final Date date;
    private final Order order;
    private final Map<String, Integer> benefit = new HashMap<>();
    private int totalDiscount;

    public Benefit(final Date date, final Order order) {
        this.date = date;
        this.order = order;
        applyEventBenefit();
    }

    private void applyEventBenefit() {
        if (10_000 <= order.getTotalPrice()) {
            christmasBenefit();
            weekDayBenefit();
            weekendBenefit();
            specialBenefit();
        }
    }

    private void christmasBenefit() {
        final int eventDate = date.getDate();
        if (eventDate <= 25) {
            final int christmasBenefit = 1000 + ((eventDate - 1) * 100);
            this.totalDiscount += christmasBenefit;
            benefit.put("크리스마스 디데이 할인", christmasBenefit);
        }
    }

    private void weekDayBenefit() {
        LocalDate weekDay = LocalDate.of(2023, 12, date.getDate());
        DayOfWeek dayOfWeek = weekDay.getDayOfWeek();
        final int eventDate = dayOfWeek.getValue();
        if (eventDate <= 4 || eventDate == 7) {
            checkWeekDayDiscountApplicable();
        }
    }

    private void checkWeekDayDiscountApplicable() {
        final List<Category> totalOrder = order.getTotalOrder();
        final int filterDessertCount = totalOrder.stream()
                .filter(category -> category.getParentCategory().equals("디저트"))
                .mapToInt(category -> order.getQuantity(category.getMenu()))
                .sum();
        final int weekDayDiscount = filterDessertCount * 2_023;
        applyWeekDayDiscount(weekDayDiscount);
    }

    private void applyWeekDayDiscount(final int weekDayDiscount) {
        if (weekDayDiscount > 0) {
            this.totalDiscount += weekDayDiscount;
            benefit.put("평일 할인", weekDayDiscount);
        }
    }

    private void weekendBenefit() {
        LocalDate weekDay = LocalDate.of(2023, 12, date.getDate());
        DayOfWeek dayOfWeek = weekDay.getDayOfWeek();
        final int eventDate = dayOfWeek.getValue();
        if (5 <= eventDate && eventDate <= 6) {
            checkWeekendDiscountApplicable();
        }
    }

    private void checkWeekendDiscountApplicable() {
        final List<Category> totalOrder = order.getTotalOrder();
        final int filterDessertCount = totalOrder.stream()
                .filter(category -> category.getParentCategory().equals("메인"))
                .mapToInt(category -> order.getQuantity(category.getMenu()))
                .sum();
        final int weekendDiscount = filterDessertCount * 2_023;
        applyWeekendDiscount(weekendDiscount);
    }

    private void applyWeekendDiscount(final int weekendDiscount) {
        if (weekendDiscount > 0) {
            this.totalDiscount += weekendDiscount;
            benefit.put("주말 할인", weekendDiscount);
        }
    }

    private void specialBenefit() {
        LocalDate weekDay = LocalDate.of(2023, 12, date.getDate());
        DayOfWeek dayOfWeek = weekDay.getDayOfWeek();
        final int eventDate = dayOfWeek.getValue();
        if (eventDate == 7 || date.getDate() == 25) {
            final int specialDiscount = 1000;
            this.totalDiscount += specialDiscount;
            benefit.put("특별 할인", specialDiscount);
        }
    }

    private int calculateDiscountedTotalPrice() {
        final int discountedTotalPrice = order.getTotalPrice() - this.totalDiscount;
        return Math.max(discountedTotalPrice, 0);
    }

    public BenefitDto toDto() {
        return new BenefitDto(
                this.benefit,
                this.totalDiscount,
                calculateDiscountedTotalPrice());
    }

    public int getDiscount() {
        return this.totalDiscount;
    }
}
