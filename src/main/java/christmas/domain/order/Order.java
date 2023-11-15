package christmas.domain.order;

import christmas.constant.errorMessage.exception.CustomIllegalArgumentException;
import christmas.constant.errorMessage.input.EventExceptionStatus;
import christmas.domain.category.Category;
import christmas.dto.OrderDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Order {

    private final Map<String, Integer> menus;

    private final int totalPrice;

    public Order(final Map<String, Integer> menus) {
        this.menus = menus;
        this.totalPrice = calculateTotalPrice(searchCategory());
    }

    private List<Category> searchCategory() {
        return menus.keySet()
                .stream()
                .map(this::compareCategoryAndMenu)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<Category> compareCategoryAndMenu(final String menu) {
        final Optional<Category> category = Category.of(menu);
        if (category.isEmpty()) {
            throw new CustomIllegalArgumentException(EventExceptionStatus.MENU_IS_NOT_CORRECT);
        }
        return category;
    }

    private int calculateTotalPrice(List<Category> categories) {
        return categories.stream()
                .mapToInt(menu -> (menu.getPrice() * getQuantity(menu.getMenu())))
                .sum();
    }

    private int getQuantity(final String menu) {
        return getOrder().get(menu);
    }

    public OrderDto toDto() {
        return new OrderDto(getOrder(), getTotalPrice());
    }

    public Map<String, Integer> getOrder() {
        return this.menus;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }
}
