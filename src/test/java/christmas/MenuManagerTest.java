package christmas;

import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static christmas.enums.menu.DessertMenu.CHOCOLATE_CAKE;
import static christmas.enums.menu.MainMenu.SEAFOOD_PASTA;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.menu.MenuItem;
import christmas.manangers.MenuManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuManagerTest {

    @DisplayName("메뉴명으로 enum 을 반환한다.")
    @Test
    void getItemByName() {
        //given
        String CHAMPAGNE_NAME = CHAMPAGNE.getName();
        String SEAFOOD_PASTA_NAME = SEAFOOD_PASTA.getName();
        String CHOCOLATE_CAKE_NAME = CHOCOLATE_CAKE.getName();

        MenuItem champagne = MenuManager.getMenuByName("샴페인");
        MenuItem seafoodPasta = MenuManager.getMenuByName("해산물파스타");
        MenuItem chocolateCake = MenuManager.getMenuByName("초코케이크");

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