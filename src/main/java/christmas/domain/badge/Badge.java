package christmas.domain.badge;

import java.util.Arrays;

public enum Badge {
    NONE("없음", 0, 0),
    STAR("별", 5_000, 10_000),
    TREE("트리", 10_000, 20_000),
    SANTA("산타", 20_000, Integer.MAX_VALUE),
    ;
    private final String name;
    private final int minPrice;
    private final int maxPrice;

    Badge(String name, int minPrice, int maxPrice) {
        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public static Badge of(int price) {
        return Arrays.stream(values())
                .filter(badge -> badge.isInRange(price))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isInRange(int price) {
        return minPrice <= price && price < maxPrice;
    }

    public String getBadgeName() {
        return name;
    }
}
