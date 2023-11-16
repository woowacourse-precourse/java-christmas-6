package christmas.promotion.domain.event.manager;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.badge.Badge;
import christmas.promotion.domain.event.gift.GiftEventsResult;
import christmas.promotion.exception.OrderEventException;
import christmas.promotion.repository.EventApplicationRepository;
import christmas.promotion.domain.event.discount.*;
import christmas.promotion.domain.event.gift.ChampagneGift;
import christmas.promotion.domain.event.gift.GiftEvent;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.order.EventfulOrder;
import christmas.promotion.domain.order.Order;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.promotion.domain.event.Event.EVENT_PARTICIPATION_THRESHOLD;

public class EventManager {
    private final LocalEventManager localEventManager;
    private final GlobalEventManager globalEventManager;
    private final BadgeManager badgeManager;
    private final EventBenefitCalculator eventBenefitCalculator;

    private final Order order;
    private final Map<Event, Price> eventBenefits;
    private final Map<Menu, Quantity> eventGifts;
    private Badge badge;

    public EventManager(final Order order) {
        this.order = order;
        this.badge = Badge.NONE;
        this.eventBenefits = new LinkedHashMap<>();
        this.eventGifts = new LinkedHashMap<>();

        this.localEventManager = LocalEventManager.INSTANCE;
        this.globalEventManager = new GlobalEventManager();
        this.badgeManager = BadgeManager.INSTANCE;
        this.eventBenefitCalculator = EventBenefitCalculator.INSTANCE;
        addEvents();
    }

    private void addEvents() {
        // 출력 순서를 유지하기 위해, LinkedHashMap 에 먼저 넣어 놓는다.
        eventBenefits.put(ChristmasDiscount.INSTANCE, Price.zero());
        eventBenefits.put(WeekdayDiscount.INSTANCE, Price.zero());
        eventBenefits.put(WeekendDiscount.INSTANCE, Price.zero());
        eventBenefits.put(SpecialDiscount.INSTANCE, Price.zero());
        eventBenefits.put(ChampagneGift.INSTANCE, Price.zero());
    }

    public void applyEvents() {
        if (isEventApplicable()) {
            applyLocalEvents();
            applyGlobalEvents();
            this.badge = badgeManager.applyEventBadge(order, getDiscountPrice(), getGiftPrice());
            updateEventDatabase();
        }
    }

    private void applyGlobalEvents() {
        applyGlobalDiscountEvents();
        applyGlobalGiftEvents();
    }

    private void applyGlobalDiscountEvents() {
        Map<Event, Price> discountEventBenefits = globalEventManager.applyDiscountEvents(order);

        for (Map.Entry<Event, Price> discountEvent : discountEventBenefits.entrySet()) {
            Price currentDiscountedPrice = eventBenefits.getOrDefault(discountEvent, Price.zero());
            eventBenefits.put(discountEvent.getKey(), currentDiscountedPrice.add(discountEvent.getValue().price()));
        }
    }

    private void applyGlobalGiftEvents() {
        GiftEventsResult giftEventResult= globalEventManager.applyGiftEvents(order);

        addGiftEventBenefits(giftEventResult);
        addGiftEventGifts(giftEventResult);
    }

    private void addGiftEventBenefits(final GiftEventsResult giftEventResult) {
        Map<Event, Price> giftEventBenefits = giftEventResult.getGiftEventBenefits();
        for (Map.Entry<Event, Price> event : giftEventBenefits.entrySet()) {
            Price currentGiftedPrice = eventBenefits.getOrDefault(event, Price.zero());
            this.eventBenefits.put(event.getKey(), currentGiftedPrice.add(event.getValue().price()));
        }
    }

    private void addGiftEventGifts(final GiftEventsResult giftEventResult) {
        Map<Menu, Quantity> giftMenus = giftEventResult.getGiftMenus();
        for (Map.Entry<Menu, Quantity> giftMenu : giftMenus.entrySet()) {
            Menu menu = giftMenu.getKey();
            Quantity quantity = giftMenu.getValue();
            this.eventGifts.put(menu, quantity);
        }
    }

    private void applyLocalEvents() {
        Map<Event, Price> OrderMenuDiscountBenefits = localEventManager.applyMenuDiscountEvents(order);

        for (Map.Entry<Event, Price> menuEvent : OrderMenuDiscountBenefits.entrySet()) {
            Price currentMenuEventPrice = eventBenefits.getOrDefault(menuEvent, Price.zero());
            eventBenefits.put(menuEvent.getKey(), currentMenuEventPrice.add(menuEvent.getValue().price()));
        }
    }

    private void updateEventDatabase() {
        /**
         * 예를 들어, 추가된 메뉴 아이스크림(디저트)이 2000원이고 5개를 사면 = 10000원 (이벤트 적용 가능)
         * 2023-12-25에 아이스크림 5개 구매
         * 크리스마스 디데이 할인: -3,400원
         * 평일 할인: -10115원
         * 특별 할인: -1,000원
         * ⇒ -4515원 (메뉴를 팔았는데, 사장이 돈을 주는 경우가 생김)
         * 이 상황을 예외 처리, isPriceNegativeAfterEvent()
         */
        if (isPriceNegativeAfterEvent()) {
            throw new OrderEventException();
        }

        EventApplicationRepository.INSTANCE.updateSalePrice(getDiscountedFinalPrice().price());
        EventApplicationRepository.INSTANCE.updateEventParticipationCount();
    }

    private boolean isPriceNegativeAfterEvent() {
        return getDiscountedFinalPrice().price() <= Price.zero().price();
    }

    private boolean isEventApplicable() {
        return order.calculateOriginalPrice().price() >= EVENT_PARTICIPATION_THRESHOLD;
    }

    private double getDiscountPrice() {
        return eventBenefits.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof DiscountEvent)
                .mapToDouble(entry -> entry.getValue().price())
                .sum();
    }

    private double getGiftPrice() {
        return eventBenefits.entrySet().stream()
                .filter(entry -> entry.getKey() instanceof GiftEvent)
                .mapToDouble(entry -> entry.getValue().price())
                .sum();
    }

    private Price getEventBenefitPrice() {
        return eventBenefitCalculator.getTotalEvnetBenefitPrice(getDiscountPrice(), getGiftPrice());
    }

    private Price getDiscountedFinalPrice() {
        return eventBenefitCalculator.getExceptedDiscountPrice(Price.of(order.calculateOriginalPrice().price() - getDiscountPrice()).price());
    }

    public EventfulOrder createEventfulOrder() {
        return new EventfulOrder(order.getOrderMenus(),
                Price.of(order.calculateOriginalPrice().price()),
                eventGifts,
                eventBenefits,
                getEventBenefitPrice(),
                getDiscountedFinalPrice(),
                badge);
    }
}
