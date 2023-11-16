package christmas.promotion.domain.order;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.dto.EventfulOrderDto;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EventfulOrder {
    private final List<OrderMenu> orderMenus; // 주문 메뉴
    private final Price originalPrice; // 할인 전 총 주문 금액
    private final Map<Menu, Quantity> giftMenus; // 증정 메뉴
    private final Map<Event, Price> eventBenefits; // 혜택 내역
    private final Price totalBenefitPrice; // 총 혜택 금액
    private final Price discountedFinalPrice; // 할인 후 예상 결제 금액
    private final Badge badge; // 배지


    public EventfulOrder(final List<OrderMenu> orderMenus,
                         final Price originalPrice,
                         final Map<Menu, Quantity> giftMenus,
                         final Map<Event, Price> eventBenefits,
                         final Price totalBenefitPrice,
                         final Price discountedPrice,
                         final Badge badge) {

        this.orderMenus = List.copyOf(orderMenus);
        this.originalPrice = originalPrice;
        this.giftMenus = Collections.unmodifiableMap(giftMenus);
        this.eventBenefits = Collections.unmodifiableMap(eventBenefits);
        this.totalBenefitPrice = totalBenefitPrice;
        this.discountedFinalPrice = discountedPrice;
        this.badge = badge;
    }

    public EventfulOrderDto toEventfulOrderDto() {
        return new EventfulOrderDto(orderMenus,
                originalPrice,
                giftMenus,
                eventBenefits,
                totalBenefitPrice,
                discountedFinalPrice,
                badge);
    }
}
