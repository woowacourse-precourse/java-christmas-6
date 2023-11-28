package christmas;

import christmas.domain.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    @Test
    void findTwoFoodsOnMenuWhichOneExistsAndTheOtherDoesNotExist() {
        String food1 = "해산물파스타";
        boolean resultBoolean1 = false;

        for(Menu menu : Menu.values()){
            if(menu.isFoodOnMenu(food1)){
                resultBoolean1 = true;
            }
        }

        boolean expectedBoolean1 = true;

        assertEquals(expectedBoolean1, resultBoolean1);

        String food2 = "김치찌개";
        boolean resultBoolean2 = false;

        for(Menu menu : Menu.values()){
            if(menu.isFoodOnMenu(food2)){
                resultBoolean2 = true;
            }
        }

        boolean expectedBoolean2 = false;

        assertEquals(expectedBoolean2, resultBoolean2);
    }

    @Test
    void findTwoFoodsThatCostThreeThousandAndFiveThousand() {
        String food1 = "제로콜라";
        int resultPrice1 = 0;

        for(Menu menu : Menu.values()) {
            resultPrice1 = menu.cost(food1);

            if(resultPrice1 != 0){
                break;
            }
        }

        int expectedPrice1 = 3000;

        assertEquals(expectedPrice1, resultPrice1);

        String food2 = "아이스크림";
        int resultPrice2 = 0;

        for(Menu menu : Menu.values()) {
            resultPrice2 = menu.cost(food2);

            if(resultPrice2 != 0){
                break;
            }
        }

        int expectedPrice2 = 5000;

        assertEquals(expectedPrice2, resultPrice2);
    }
}
