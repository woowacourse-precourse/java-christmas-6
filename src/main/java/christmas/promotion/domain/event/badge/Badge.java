package christmas.promotion.domain.event.badge;

import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.vo.Price;

import java.time.LocalDate;

/**
 * 배지를 적용할 때, for 문으로 돌면서 가장 큰 값에 적용 가능하면 바로 리턴해야한다.
 * BadgeManger 코드 참고
 */
public enum Badge implements GlobalEvent<Badge> {
    SANTA("산타", 20000.0),
    TREE("트리", 10000.0),
    STAR("별", 5000.0),
    NONE("없음", 0.0);

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);

    private final String name;
    private final double threshold;


    Badge(final String name, final double threshold) {
        this.name = name;
        this.threshold = threshold;  // 수정: Badge.None 대신에 threshold 값을 할당
    }

    public String getName() {
        return name;
    }

    @Override
    public String getEventName() {
        return "배지 증정 이벤트";
    }

    @Override
    public Badge applyEvent(final DecemberVisitDate date, final Price eventfulPrice) {
        if (isPossibleEvent(date, eventfulPrice)) {
            return this;
        }

        return NONE;
    }

    @Override
    public boolean isPossibleEvent(final DecemberVisitDate date, final Price eventfulPrice) {
        return isBetweenDates(date) && eventfulPrice.price() >= this.threshold;
    }

    @Override
    public boolean isBetweenDates(final DecemberVisitDate date) {
        return date.isBetweenDates(EVENT_PERIOD_START, EVENT_PERIOD_END);
    }
}
