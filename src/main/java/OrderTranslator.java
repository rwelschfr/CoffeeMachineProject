import java.math.BigDecimal;

public class OrderTranslator {

    private DrinkMaker drinkMaker;

    public boolean translateOrder(BeverageOrder beverageOrder) {
        BigDecimal moneyGiven = beverageOrder.getMoneyGiven();
        BigDecimal price = beverageOrder.getBeverageType().getPrice();
        if(moneyGiven.compareTo(price) >= 0) {
            String command = beverageOrder.getBeverageType().getCommandPrefix();
            if(beverageOrder.getSugarAmount() > 0) {
                command = command.concat(":" + beverageOrder.getSugarAmount() + ":0");
            } else {
                command = command.concat("::");
            }
            drinkMaker.makeDrinks(command);
            return true;
        } else {
            BigDecimal missingMoney = price.subtract(moneyGiven);
            sendMessage("Please insert " + missingMoney.doubleValue() + " euros");
            return false;
        }
    }

    public boolean sendMessage(String message) {
        String command = "M:" + message;
        drinkMaker.makeDrinks(command);
        return true;
    }
}
