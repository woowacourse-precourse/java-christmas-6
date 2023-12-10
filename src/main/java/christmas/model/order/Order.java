package christmas.model.order;

import static christmas.common.Constant.MENU_COUNT_MAX;
import static christmas.common.ExceptionMessage.ERROR_DUPLICATE_MENU_SELECTION;
import static christmas.common.ExceptionMessage.ERROR_MENU_COUNT_MAXIMUM_EXCEEDED;
import static christmas.common.ExceptionMessage.ERROR_MENU_ONLY_BEVERAGE;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuCategory;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.stream.Collectors;

public class Order {
    private final VisitDate visitDate;
    private final EnumMap<Menu, MenuCount> orders;

    private Order(VisitDate visitDate, EnumMap<Menu, MenuCount> orders) {
        validate(orders);

        this.visitDate = visitDate;
        this.orders = orders;
    }

    public static Order from(VisitDate visitDate, String orders) {
        return new Order(visitDate, parseOrder(orders));
    }

    // TODO : order parser 만들기
    // TODO : 메뉴 중복 예외처리 추출할 방법 생각하기
    private static EnumMap<Menu, MenuCount> parseOrder(String orders) {
        return Arrays.stream(orders.split(","))
                .map(order -> order.split("-"))
                .collect(Collectors.toMap(
                        split -> Menu.nameOf(split[0].trim()),
                        split -> new MenuCount(Integer.parseInt(split[1].trim())),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException(ERROR_DUPLICATE_MENU_SELECTION);
                        },
                        () -> new EnumMap<>(Menu.class)
                ));
    }

    private void validate(EnumMap<Menu, MenuCount> orders) {
        if (exceedsMaximumMenuCount(orders)) {
            throw new IllegalArgumentException(String.format(ERROR_MENU_COUNT_MAXIMUM_EXCEEDED, MENU_COUNT_MAX));
        }

        if (isMenuOnlyBeverage(orders)) {
            throw new IllegalArgumentException(ERROR_MENU_ONLY_BEVERAGE);
        }
    }

    private boolean exceedsMaximumMenuCount(EnumMap<Menu, MenuCount> orders) {
        int totalMenuCount = orders.values()
                .stream()
                .mapToInt(MenuCount::getCount)
                .sum();

        return totalMenuCount > MENU_COUNT_MAX;
    }

    private boolean isMenuOnlyBeverage(EnumMap<Menu, MenuCount> orders) {
        return orders.keySet()
                .stream()
                .allMatch(menu -> menu.getMenuCategory().equals(MenuCategory.BEVERAGE));
    }

    public boolean isTotalAmountMoreThan(double threshold) {
        return getTotalAmount() >= threshold;
    }

    public int getMenuCount(MenuCategory category) {
        return orders.entrySet()
                .stream()
                .filter(order -> order.getKey().getMenuCategory().equals(category))
                .mapToInt(order -> order.getValue().getCount())
                .sum();
    }

    public double getTotalAmount() {
        return orders.entrySet()
                .stream()
                .mapToDouble(order -> order.getKey().getPrice() * order.getValue().getCount())
                .sum();
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public EnumMap<Menu, MenuCount> getOrders() {
        return orders;
    }
}
