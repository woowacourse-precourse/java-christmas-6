package christmas.controller.dto;

import java.util.List;

public record PromotionsResult(
        List<PromotionResult> promotionResults
) {
}
