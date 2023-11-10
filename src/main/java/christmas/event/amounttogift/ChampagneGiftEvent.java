package christmas.event.amounttogift;

import java.time.LocalDate;

public class ChampagneGiftEvent implements AmountToGiftEventInterface {
    private final static Integer DEFAULT_GIFT_AMOUNT = 120_000;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public ChampagneGiftEvent(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isGiftAmountOver(Integer totalOrderAmount) {
        if (totalOrderAmount > DEFAULT_GIFT_AMOUNT) {
            return true;
        }
        return false;
    }
    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate));
    }

    @Override
    public Boolean execute(LocalDate reservationDate, Integer totalOrderAmount) {
        if (isEventActivate(reservationDate)) {
            return isGiftAmountOver(totalOrderAmount);
        }
        return false;
    }
}
