package christmas.promotion.repository;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 이벤트 목표 달성 여부를 알기 위해 만든 클래스
 * 목표 1. 올해 12월에 지난 5년 중 최고의 판매 금액을 달성 - eventfulMenuSalePrice
 * 목표 2. 12월 이벤트 참여 고객의 5%가 내년 1월 새해 이벤트에 재참여하는 것 - eventParticipationCount*
 * List<eventfullOrder> 를 저장 해도 되지만, 목표는 위의 2가지 이므로 우선 위 2개의 정보만 저장했다.
 */

public enum EventApplicationRepository {
    INSTANCE;

    // 스레드 세이프하기 위해, DoubleAdder, AtomicInteger 이용
    private final DoubleAdder eventfulMenuSalePrice = new DoubleAdder();
    private final AtomicInteger eventParticipationCount = new AtomicInteger(0);

    public void updateSalePrice(final double eventfulMenuSalePrice) {
        this.eventfulMenuSalePrice.add(eventfulMenuSalePrice);
    }

    public void updateEventParticipationCount() {
        eventParticipationCount.incrementAndGet();
    }

    public double getSalePrice() {
        return eventfulMenuSalePrice.sum();
    }

    public int getEventParticipationCount() {
        return eventParticipationCount.get();
    }
}