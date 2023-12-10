package christmas.view.dto;

import christmas.model.menu.Menu;
import christmas.model.order.MenuCount;
import christmas.model.promotion.Promotion;
import java.util.EnumMap;

// TODO: 자료형으로 그대로 넣는게 맞나?
public record ReceiptResponseDto(
        int visitMonth,
        int visitDay,
        EnumMap<Menu, MenuCount> orders,
        int totalAmountBeforeDiscount,
        EnumMap<Menu, Integer> freebies,
        EnumMap<Promotion, Double> benefitRecords,
        int totalBenefitAmount,
        int estimatedPaymentAmount,
        String badgeName) {
}
