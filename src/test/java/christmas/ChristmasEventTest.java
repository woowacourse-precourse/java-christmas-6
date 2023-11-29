package christmas;

import christmas.domain.ChristmasEvent;
import christmas.domain.OrderList;
import christmas.domain.ReservationDate;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChristmasEventTest {
    @Test
    void orderBeforeDecemberTwentySixth() {
        int expectedDDayDiscount1 = 1000;
        assertDDayDiscount(new OrderList(new String[] {"해산물파스타-1"}), new ReservationDate(1), expectedDDayDiscount1);
        int expectedDDayDiscount2 = 2000;
        assertDDayDiscount(new OrderList(new String[] {"크리스마스파스타-1"}), new ReservationDate(11), expectedDDayDiscount2);
    }

    private void assertDDayDiscount(OrderList orderList, ReservationDate reservationDate, int expectedDiscount) {
        ChristmasEvent christmasEvent = new ChristmasEvent(orderList, reservationDate);
        int realDiscount = christmasEvent.calculateDDayDiscount();
        assertEquals(expectedDiscount, realDiscount);
    }

    @Test
    void orderAfterChristmas() {
        int expectedDDayDiscount = 0;
        assertDDayDiscount(new OrderList(new String[] {"해산물파스타-1"}), new ReservationDate(26), expectedDDayDiscount);
    }

    @Test
    void orderMoreThanTenThousandBeforeChristmas() {
        int expectedDDayDiscount1 = 1000;
        assertDDayDiscount(new OrderList(new String[] {"해산물파스타-1"}), new ReservationDate(1), expectedDDayDiscount1);
    }

    @Test
    void orderUnderTenThousandBeforeChristmas() {
        int expectedDDayDiscount1 = 0;
        assertDDayDiscount(new OrderList(new String[] {"타파스-1"}), new ReservationDate(1), expectedDDayDiscount1);
    }
}
