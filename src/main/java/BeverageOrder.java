public class BeverageOrder {

    public enum BeverageType {
        TEA, COFFEE, CHOCOLATE;
    }

    private BeverageType beverageType;

    private int sugarAmount;

    private boolean hasStick;

    public BeverageOrder(BeverageType beverageType, int sugarAmount, boolean hasStick) {
        this.beverageType = beverageType;
        this.sugarAmount = sugarAmount;
        this.hasStick = hasStick;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public int getSugarAmount() {
        return sugarAmount;
    }

    public boolean isHasStick() {
        return hasStick;
    }
}
