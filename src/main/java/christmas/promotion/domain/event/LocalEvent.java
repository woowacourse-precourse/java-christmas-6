package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;

public interface LocalEvent<R> extends Event {
    boolean isPossibleEvent(final DecemberVisitDate date);

    R applyEvent(final DecemberVisitDate date);
}