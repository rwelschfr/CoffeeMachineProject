import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DrinkMakerStatistics {

    private BigDecimal moneyEarned = BigDecimal.ZERO;

    private Map<BeverageType, Integer> salesMap = new HashMap<>();

    public DrinkMakerStatistics() {
        for(BeverageType beverageType : BeverageType.values()) {
            salesMap.put(beverageType, 0);
        }
    }

    public BigDecimal getMoneyEarned() {
        return moneyEarned;
    }

    public Map<BeverageType, Integer> getSalesMap() {
        return salesMap;
    }

    public void setMoneyEarned(BigDecimal moneyEarned) {
        this.moneyEarned = moneyEarned;
    }
}
