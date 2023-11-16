package christmas.promotion.vo;

import java.util.HashMap;
import java.util.Map;

public record Price(Double price) {
    // 캐시된 Price 객체를 저장할 맵
    private static final Map<Double, Price> priceCache = new HashMap<>();

    // 생성자 대신 팩토리 메서드 사용
    public static Price of(final Double price) {
        return priceCache.computeIfAbsent(price, Price::new);
    }

    public Price add(final double price) {
        return of(this.price + price);
    }

    public static Price zero() {
        return of(0.0);
    }

    public Price add(final Price price) {
        return Price.of(this.price + price.price);
    }
}
