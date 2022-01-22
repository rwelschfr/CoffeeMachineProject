import java.math.BigDecimal;

public enum BeverageType {

    TEA(new BigDecimal("0.4"), "T"),
    COFFEE(new BigDecimal("0.6"), "C"),
    CHOCOLATE(new BigDecimal("0.5"), "H"),
    ORANGE_JUICE(new BigDecimal("0.6"), "O"),
    TEA_EXTRA_HOT(new BigDecimal("0.4"), "Th"),
    COFFEE_EXTRA_HOT(new BigDecimal("0.6"), "Ch"),
    CHOCOLATE_EXTRA_HOT(new BigDecimal("0.5"), "Hh");

    private BigDecimal price;

    private String commandPrefix;

    BeverageType(BigDecimal price, String commandPrefix) {
        this.price = price;
        this.commandPrefix = commandPrefix;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }
}
