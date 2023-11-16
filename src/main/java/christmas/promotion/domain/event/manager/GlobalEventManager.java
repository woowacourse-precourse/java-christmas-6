package christmas.promotion.domain.event.manager;


import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.domain.event.discount.ChristmasDiscount;
import christmas.promotion.domain.event.discount.DiscountEvent;
import christmas.promotion.domain.event.discount.SpecialDiscount;
import christmas.promotion.domain.event.gift.ChampagneGift;
import christmas.promotion.domain.event.gift.GiftEvent;
import christmas.promotion.domain.event.gift.GiftEventsResult;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.order.Order;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.promotion.domain.event.Event.NON_DISCOUNT_EVENT;
import static christmas.promotion.domain.event.Event.NON_GIFT_EVENT;

public class GlobalEventManager {
    private final Map<GlobalEvent, Double> globalEvents;

    public GlobalEventManager() {
        this.globalEvents = new LinkedHashMap<>();
        addGlobalEvents();
    }

    private void addGlobalEvents() {
        globalEvents.put(ChristmasDiscount.INSTANCE, 0.0);
        globalEvents.put(SpecialDiscount.INSTANCE, 0.0);
        globalEvents.put(ChampagneGift.INSTANCE, 0.0);
    }

    public Map<Event, Price> applyDiscountEvents(final Order order) {
        Map<Event, Price> discountEventBenefits = new HashMap<>();

        for (GlobalEvent event : globalEvents.keySet()) {
            if (event instanceof DiscountEvent) {
                applyDiscountEvent(order, event, discountEventBenefits);
            }
        }

        return discountEventBenefits;
    }

    private void applyDiscountEvent(final Order order,
                                    final GlobalEvent event,
                                    final Map<Event, Price> eventBenefits) {

        Price discountPrice = (Price) event.applyEvent(order.getDate(), order.calculateOriginalPrice());

        if (discountPrice.price() > NON_DISCOUNT_EVENT.price()) { // 할인을 한 경우
            Price currentPrice = eventBenefits.getOrDefault(event, Price.zero());
            eventBenefits.put(event, currentPrice.add(discountPrice.price()));
        }
    }

    public GiftEventsResult applyGiftEvents(final Order order) {
        Map<Event, Price> giftEventBenefits = new HashMap<>();
        Map<Menu, Quantity> giftMenus = new HashMap<>();
        for (GlobalEvent globalEvent : globalEvents.keySet()) {
            if (globalEvent instanceof GiftEvent) {
                applyGiftEvent(order, globalEvent, giftEventBenefits, giftMenus);
            }
        }

        return new GiftEventsResult(giftEventBenefits, giftMenus);
    }

    private void applyGiftEvent(final Order order,
                                final GlobalEvent giftEvent,
                                final Map<Event, Price> giftEventBenefits,
                                final Map<Menu, Quantity> giftMenus) {

        Price giftPrice = (Price) giftEvent.applyEvent(order.getDate(), order.calculateOriginalPrice());

        if (giftPrice.price() > NON_GIFT_EVENT.price()) { // 선물을 증정한 경우
            Price currentPrice = giftEventBenefits.getOrDefault(giftEvent, Price.zero()); // 이미 같은 선물이 있는 경우 덧 붙이기
            giftEventBenefits.put(giftEvent, currentPrice.add(giftPrice.price()));
            addGiftMenu((GiftEvent) giftEvent, giftMenus);
        }
    }

    private void addGiftMenu(final GiftEvent giftEvent, final Map<Menu, Quantity> giftMenus) {
        Menu menu = giftEvent.getGiftMenu();
        Quantity quantity = new Quantity(giftEvent.getGiftQuantity());
        giftMenus.put(menu, quantity);
    }
}

