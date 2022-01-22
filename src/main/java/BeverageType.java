import java.math.BigDecimal;

public enum BeverageType {

    TEA(new BigDecimal("0.4")),
    COFFEE(new BigDecimal("0.6")),
    CHOCOLATE(new BigDecimal("0.5"));

    private BigDecimal price;

    BeverageType(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
