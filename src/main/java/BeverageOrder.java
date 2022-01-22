import java.math.BigDecimal;

public class BeverageOrder {

    private BeverageType beverageType;

    private int sugarAmount;

    private BigDecimal moneyGiven;

    public BeverageOrder(BeverageType beverageType, int sugarAmount, BigDecimal moneyGiven) {
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

    public BigDecimal getMoneyGiven() {
        return moneyGiven;
    }
}
