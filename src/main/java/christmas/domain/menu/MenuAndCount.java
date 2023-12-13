package christmas.domain.menu;

public class MenuAndCount {
    private final Menu menu;
    private final int count;

    private MenuAndCount(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public static MenuAndCount from(String order){
        String[] split = order.split("-");
        Menu menu = Menu.from(split[0]);
        int count = Integer.parseInt(split[1]);
        return new MenuAndCount(menu, count);
    }

    public int calcPrice() {
        return menu.getPrice() * count;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getCount() {
        return count;
    }
}
