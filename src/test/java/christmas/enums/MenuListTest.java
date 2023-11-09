package christmas.enums;

import static christmas.enums.BeverageMenu.CHAMPAGNE;
import static christmas.enums.DessertMenu.CHOCOLATE_CAKE;
import static christmas.enums.MainMenu.SEAFOOD_PASTA;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuListTest {

    @DisplayName("메뉴명으로 enum 을 반환한다.")
    @Test
    void getItemByName() {
        String CHAMPAGNE_NAME = CHAMPAGNE.getName();
        String SEAFOOD_PASTA_NAME = SEAFOOD_PASTA.getName();
        String CHOCOLATE_CAKE_NAME = CHOCOLATE_CAKE.getName();

        MenuItem champagne = MenuList.getMenuByName("샴페인");
        MenuItem seafoodPasta = MenuList.getMenuByName("해산물파스타");
        MenuItem chocolateCake = MenuList.getMenuByName("초코케이크");

        //when
        String champagneName = champagne.getName();
        String seafoodPastaName = seafoodPasta.getName();
        String chocolateCakeName = chocolateCake.getName();

        //then
        assertThat(champagneName).isEqualTo(CHAMPAGNE_NAME);
        assertThat(seafoodPastaName).isEqualTo(SEAFOOD_PASTA_NAME);
        assertThat(chocolateCakeName).isEqualTo(CHOCOLATE_CAKE_NAME);

    }
}