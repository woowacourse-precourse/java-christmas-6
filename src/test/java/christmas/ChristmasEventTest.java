package christmas;

import christmas.domain.ChristmasEvent;
import christmas.domain.OrderList;
import christmas.domain.ReservationDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChristmasEventTest {
    @Test
    void orderBeforeDecemberTwentySixth() {
        ReservationDate reservationDate1 = new ReservationDate(1);
        OrderList orderList1 = new OrderList(new String[] {"해산물파스타-1"});
        ChristmasEvent christmasEvent1 = new ChristmasEvent(orderList1, reservationDate1);
        int resultDDayDiscount1 = christmasEvent1.calculateDDayDiscount();

        int expectedDDayDiscount1 = 1000;

        assertEquals(expectedDDayDiscount1, resultDDayDiscount1);

        ReservationDate reservationDate2 = new ReservationDate(11);
        OrderList orderList2 = new OrderList(new String[] {"크리스마스파스타-1"});
        ChristmasEvent christmasEvent2 = new ChristmasEvent(orderList2, reservationDate2);
        int resultDDayDiscount2 = christmasEvent2.calculateDDayDiscount();

        int expectedDDayDiscount2 = 2000;

        assertEquals(expectedDDayDiscount2, resultDDayDiscount2);
    }

    @Test
    void orderAfterChristmas() {
        ReservationDate reservationDate1 = new ReservationDate(26);
        OrderList orderList1 = new OrderList(new String[] {"해산물파스타-1"});
        ChristmasEvent christmasEvent1 = new ChristmasEvent(orderList1, reservationDate1);
        int resultDDayDiscount1 = christmasEvent1.calculateDDayDiscount();

        int expectedDDayDiscount1 = 0;

        assertEquals(expectedDDayDiscount1, resultDDayDiscount1);
    }

    @Test
    void orderMoreThanTenThousandBeforeChristmas() {
        ReservationDate reservationDate1 = new ReservationDate(1);
        OrderList orderList1 = new OrderList(new String[] {"해산물파스타-1"});
        ChristmasEvent christmasEvent1 = new ChristmasEvent(orderList1, reservationDate1);
        int resultDDayDiscount1 = christmasEvent1.calculateDDayDiscount();

        int expectedDDayDiscount1 = 1000;

        assertEquals(expectedDDayDiscount1, resultDDayDiscount1);
    }

    @Test
    void orderUnderTenThousandBeforeChristmas() {
        //TO DO 1 : Ddaycountodown 이벤트 마지막 기능 이벤총주문 금액 10000원 이하에서만 작동하는지 확인
        ReservationDate reservationDate1 = new ReservationDate(1);
        OrderList orderList1 = new OrderList(new String[] {"타파스-1"});
        ChristmasEvent christmasEvent1 = new ChristmasEvent(orderList1, reservationDate1);
        int resultDDayDiscount1 = christmasEvent1.calculateDDayDiscount();

        int expectedDDayDiscount1 = 0;

        assertEquals(expectedDDayDiscount1, resultDDayDiscount1);
    }
}
