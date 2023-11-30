package christmas;

import christmas.domain.Menu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    @Test
    void findTwoFoodsOnMenuWhichOneExistsAndTheOtherDoesNotExist() {
        boolean expectation1 = true;
        assertFoodIsOnMenu("해산물파스타", expectation1);
        boolean expectation2 = false;
        assertFoodIsOnMenu("김치찌개", expectation2);
    }

    private void assertFoodIsOnMenu(String food, boolean expectation) {
        boolean isFoodOnMenu = false;
        for(Menu menu : Menu.values()){
            if(menu.isFoodOnMenu(food)){
                isFoodOnMenu = true;
                break;
            }
        }
        assertEquals(expectation, isFoodOnMenu);
    }

    @Test
    void findTwoFoodsThatCostThreeThousandAndFiveThousand() {
        int expectedPrice1 = 3000;
        assertPrice("제로콜라", expectedPrice1);
        int expectedPrice2 = 5000;
        assertPrice("아이스크림", expectedPrice2);
    }

    private void assertPrice(String food, int expectedPrice) {
        int realPrice = 0;

        for(Menu menu : Menu.values()) {
            realPrice = menu.cost(food);
            if(realPrice != 0){
                break;
            }
        }

        assertEquals(realPrice, expectedPrice);
    }

    @Test
    void returnMenuTypeWhenOrderDessert() {
        String expectedMenuType1 = "dessert";
        assertMenuType("아이스크림", expectedMenuType1);

        String expectedMenuType2 = "appetizer";
        assertMenuType("양송이수프", expectedMenuType2);
    }

    private void assertMenuType(String food, String expectedMenuType) {
        String realMenuType = "";
        for(Menu menu : Menu.values()) {
            realMenuType = menu.findMenuType(food);
            if(!realMenuType.equals("")){
                break;
            }
        }

        assertEquals(expectedMenuType, realMenuType);
    }
}
