package christmas.model.order;

import static christmas.common.Constant.MENU_COUNT_MIN;
import static christmas.common.ExceptionMessage.ERROR_MENU_COUNT_LESS_THAN_MINIMUM;

import java.util.Objects;

public class MenuCount {
    private final int count;

    public MenuCount(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (!isOverMinimum(count)) {
            throw new IllegalArgumentException(String.format(ERROR_MENU_COUNT_LESS_THAN_MINIMUM, MENU_COUNT_MIN));
        }
    }

    private boolean isOverMinimum(int count) {
        return count >= MENU_COUNT_MIN;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuCount menuCount = (MenuCount) o;
        return count == menuCount.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
