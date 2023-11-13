package christmas.enums.menu;


public interface MenuItem {
    String getName();

    Integer getAmount();

    default boolean isNone(){
       return this.equals(NoMenu.NO_MENU);
    }
}
