package christmas.utils;

public enum Delimiter {

    COMMA(","),
    BAR("-");

    private final String unit;

    Delimiter(final String unit) {
        this.unit = unit;
    }

    public static String[] splitWithComma(final String target) {
        return target.split(COMMA.unit);
    }

    public static String[] splitWithBar(final String target) {
        return target.split(BAR.unit);
    }
}
