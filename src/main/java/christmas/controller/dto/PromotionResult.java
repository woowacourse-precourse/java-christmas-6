package christmas.controller.dto;

import christmas.domain.Benefit;
import christmas.domain.constants.Promotion;

public record PromotionResult(
        Promotion promotion,
        Benefit benefit
) {
}
