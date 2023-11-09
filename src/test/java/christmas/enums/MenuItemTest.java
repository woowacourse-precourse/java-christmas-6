package christmas.enums;

import static christmas.enums.BeverageMenu.CHAMPAGNE;
import static christmas.enums.DessertMenu.*;
import static christmas.enums.MainMenu.*;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuItemTest {

    @DisplayName("음식명으로 해당 Enum 반환")
    @Test
    void createByName() {
        String CHAMPAGNE_NAME = CHAMPAGNE.getName();
        String SEAFOOD_PASTA_NAME = SEAFOOD_PASTA.getName();
        String CHOCOLATE_CAKE_NAME = CHOCOLATE_CAKE.getName();
        //given
        MenuItem champagne = MenuItem.getByName("샴페인");
        MenuItem seaFoodPasta = MenuItem.getByName("해산물파스타");
        MenuItem chocoCake = MenuItem.getByName("초코케이크");

        //when
        String champagneName = champagne.getName();
        String seaFoodPastaName = seaFoodPasta.getName();
        String chocoCakeName = chocoCake.getName();

        //then
        assertThat(champagneName).isEqualTo(CHAMPAGNE_NAME);
        assertThat(seaFoodPastaName).isEqualTo(SEAFOOD_PASTA_NAME);
        assertThat(chocoCakeName).isEqualTo(CHOCOLATE_CAKE_NAME);

    }
}