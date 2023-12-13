package christmas.domain.money;

public interface Monetary extends Comparable<Monetary> {

    @Override
    default int compareTo(Monetary o){
        return this.getPrice() - o.getPrice();
    };

    int getPrice();
}
