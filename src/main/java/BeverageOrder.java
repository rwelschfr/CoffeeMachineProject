public class BeverageOrder {

    private BeverageType beverageType;

    private int sugarAmount;

    private double moneyGiven;

    public BeverageOrder(BeverageType beverageType, int sugarAmount, double moneyGiven) {
        this.beverageType = beverageType;
        this.sugarAmount = sugarAmount;
        this.moneyGiven = moneyGiven;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public int getSugarAmount() {
        return sugarAmount;
    }

    public double getMoneyGiven() {
        return moneyGiven;
    }
}
