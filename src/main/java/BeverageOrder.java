public class BeverageOrder {

    public enum BeverageType {
        TEA, COFFEE, CHOCOLATE;
    }

    private BeverageType beverageType;

    private int sugarAmount;

    public BeverageOrder(BeverageType beverageType, int sugarAmount) {
        this.beverageType = beverageType;
        this.sugarAmount = sugarAmount;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public int getSugarAmount() {
        return sugarAmount;
    }
}
