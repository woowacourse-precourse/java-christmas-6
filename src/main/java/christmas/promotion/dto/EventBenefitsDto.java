package christmas.promotion.dto;

import christmas.promotion.domain.event.Event;
import christmas.promotion.vo.Price;

import java.util.Map;

public record EventBenefitsDto(Map<Event, Price> eventBenefits) {
}
