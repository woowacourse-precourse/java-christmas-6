package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.vo.Price;

public interface GlobalEvent<R> extends Event {
    boolean isPossibleEvent(final DecemberVisitDate date, final Price value);

    R applyEvent(final DecemberVisitDate date, final Price value);
}
