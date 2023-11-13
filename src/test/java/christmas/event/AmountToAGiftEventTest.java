package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.GIFT_CONDITION_BENEFIT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.menu.MenuItem;
import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountToAGiftEventTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 15);
    private final static Integer OVER_120_000 = 120_000;
    private final static Integer UNDER_120_000 = 110_000;
    private final static AmountToAGiftEvent champagneGiftEvent =
            new AmountToAGiftEvent(eventPeriod, GIFT_CONDITION_BENEFIT.getAmount(), CHAMPAGNE, 2);
    private final static Integer DOUBLE_CHAMPAGNE = CHAMPAGNE.getAmount() * 2;


    @DisplayName("120,000원 이상이면 2개의 증정품, 그리고 혜택금액을 반환한다.")
    @Test
    void isGiftAmountOver() {
        //when
        Gift gift = champagneGiftEvent.execute(reservationDate, OVER_120_000);
        Integer giftDiscountBenefit = gift.getGiftDiscountBenefit();
        Integer quantity = gift.quantity();
        MenuItem menuItem = gift.menuItem();

        //then
        assertThat(giftDiscountBenefit).isEqualTo(DOUBLE_CHAMPAGNE);
        assertThat(quantity).isEqualTo(2);
        assertThat(menuItem).isEqualTo(CHAMPAGNE);
    }

    @DisplayName("120,000원 미만이면 증정품을 반환하지 않는다.")
    @Test
    void isGiftAmountUnder() {
        //when
        Gift gift = champagneGiftEvent.execute(reservationDate, UNDER_120_000);

        //then
        assertThat(gift).isEqualTo(Gift.NO_GIFT());
    }
}