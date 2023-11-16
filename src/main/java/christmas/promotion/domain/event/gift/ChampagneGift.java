package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.domain.menu.Beverage;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.vo.Price;
import christmas.promotion.domain.visitdate.DecemberVisitDate;

import java.time.LocalDate;

public enum ChampagneGift implements GlobalEvent<Price>, GiftEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final Price THRESHOLD = Price.of(120_000.0);
    private static final Menu GIFT_MENU = Beverage.CHAMPAGNE;  // 수정: Beverage.CHAMPAGNE -> Menu.CHAMPAGNE
    private static final int GIFT_QUANTITY = 1;

    @Override
    public Menu getGiftMenu() {
        return GIFT_MENU;
    }

    @Override
    public Integer getGiftQuantity() {
        return GIFT_QUANTITY;
    }

    @Override
    public Price applyEvent(final DecemberVisitDate date, final Price price) {
        if (!isPossibleEvent(date, price)) {
            return NON_GIFT_EVENT;
        }

        return Price.of(GIFT_MENU.getPrice() * GIFT_QUANTITY);
    }


    @Override
    public boolean isPossibleEvent(final DecemberVisitDate date, final Price price) {
        return isBetweenDates(date) && isPriceThresholdAboveOrEqual(price);
    }

    @Override
    public boolean isBetweenDates(DecemberVisitDate date) {
        return date.isBetweenDates(EVENT_PERIOD_START, EVENT_PERIOD_END);
    }

    private boolean isPriceThresholdAboveOrEqual(Price price) {
        return price.price() >= THRESHOLD.price();
    }
}
