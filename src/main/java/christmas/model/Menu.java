package christmas.model;

import java.util.Objects;

public class Menu {

    Category category;

    String menuName;

    int price;

    public Menu(Category category, String menuName, int price) {
        this.category = category;
        this.menuName = menuName;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || menuName.getClass() != o.getClass()) return false;
        String menu = (String) o;
        return this.menuName.equals(menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuName);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "category=" + category +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                '}';
    }
}

// TODO : 메뉴 이름 입력받아 Contains 메서드로 포함여부 확인하기 위해, equals 메서드 오버 로딩