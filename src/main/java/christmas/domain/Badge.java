package christmas.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int threshold;

    Badge(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static Badge from(int price) {
        return Arrays.stream(Badge.values())
                .filter(b -> price >= b.threshold)
                .max(Comparator.comparingInt(b -> b.threshold))
                .orElse(null);
    }

    public String getName() {
        return name;
    }
}
