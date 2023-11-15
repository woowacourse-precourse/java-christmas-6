package christmas.domain.services;

import christmas.domain.entity.Benefits;
import christmas.domain.entity.Order;
import christmas.domain.generators.OrderGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountPolicyTest {

    @DisplayName("날짜가 주어지면 크리스마스 디데이 이벤트의 할인 금액이 적용된다.")
    @Test
    void dDayDiscountTest(){
        //Assert
        Order.emptyMap();
        OrderGenerator orderGenerator = new OrderGenerator();
        DiscountPolicy discountPolicy = new DiscountPolicy(
                orderGenerator.generateOrder("티본스테이크-1", 7)
        );

        //Act
        discountPolicy.dDayDiscount();

        //Assert
        assertThat(Benefits.getdDayDiscountAmount()).isEqualTo(1600);
    }

    @DisplayName("방문일이 평일인 경우 주중 할인 금액이 적용된다.")
    @Test
    void weekDayDiscountTest(){
        //Assert
        Order.emptyMap();
        OrderGenerator orderGenerator = new OrderGenerator();
        DiscountPolicy discountPolicy = new DiscountPolicy(
                orderGenerator.generateOrder("초코케이크-2", 7)
        );

        //Act
        discountPolicy.weekDayDiscount();

        //Assert
        assertThat(Benefits.getWeekdayDiscountAmount()).isEqualTo(4046);
    }

    @DisplayName("방문일이 주말인 경우 주말 할인 금액이 적용된다.")
    @Test
    void weekendDiscountTest(){
        //Assert
        Order.emptyMap();
        OrderGenerator orderGenerator = new OrderGenerator();
        DiscountPolicy discountPolicy = new DiscountPolicy(
                orderGenerator.generateOrder("티본스테이크-1", 8)
        );

        //Act
        discountPolicy.weekendDiscount();

        //Assert
        assertThat(Benefits.getWeekendDiscountAmount()).isEqualTo(2023);
    }

    @DisplayName("방문일이 특별일인 경우 특별 할인 금액이 적용된다.")
    @Test
    void specialDiscountTest(){
        //Assert
        Order.emptyMap();
        OrderGenerator orderGenerator = new OrderGenerator();
        DiscountPolicy discountPolicy = new DiscountPolicy(
                orderGenerator.generateOrder("티본스테이크-1", 25)
        );

        //Act
        discountPolicy.specialDiscount();

        //Assert
        assertThat(Benefits.getSpecialDayDiscountAmount()).isEqualTo(1000);
    }

    @DisplayName("할인 전 총주문 금액이 120000원 이상일 경우 샴페인을 증정한다.")
    @Test
    void giftEventTest(){
        //Assert
        Order.emptyMap();
        OrderGenerator orderGenerator = new OrderGenerator();
        DiscountPolicy discountPolicy = new DiscountPolicy(
                orderGenerator.generateOrder("티본스테이크-3", 7)
        );

        //Act
        discountPolicy.giftEvent();

        //Assert
        assertThat(Benefits.getGiftMenu()).isEqualTo("샴페인");
    }

    @DisplayName("할인혜택 총액에 따라 뱃지를 부여받는다.")
    @Test
    void eventBadgeTest(){
        //Assert
        Order.emptyMap();
        OrderGenerator orderGenerator = new OrderGenerator();
        DiscountPolicy discountPolicy = new DiscountPolicy(
                orderGenerator.generateOrder("티본스테이크-3", 7)
        );
        discountPolicy.giftEvent();
        Benefits.accountTotalBenefit();

        //Act
        discountPolicy.getDecemberEventBadge();

        //Assert
        assertThat(Benefits.getDecemberEventBadge()).isEqualTo("산타");
    }
}
