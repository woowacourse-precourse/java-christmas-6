package christmas.domain.menu;

import christmas.exception.PromotionExceptionMaker;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Menu {
    // 애피타이저
    YANGSONGEE_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000),
    BARBECUE_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),

    // 디저트
    CHOCO_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),

    // 음료
    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private static final Map<String, Menu> cachedMap = Arrays.stream(values())
            .collect(Collectors.toMap(Menu::getName, Function.identity()));

    private final String name;

    private final int price;
    Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Menu from(String name) {
        return Optional.ofNullable(cachedMap.get(name))
                .orElseThrow(PromotionExceptionMaker.NO_SUCH_MENU::makeException);
    }

    public String getName() {
        return name;
    }
}
