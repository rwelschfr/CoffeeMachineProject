import java.math.BigDecimal;
import java.util.Map;

public class OrderTranslator {

    private DrinkMaker drinkMaker;

    private BeverageQuantityChecker beverageQuantityChecker;

    private EmailNotifier emailNotifier;

    private DrinkMakerStatistics drinkMakerStatistics = new DrinkMakerStatistics();

    public boolean translateOrder(BeverageOrder beverageOrder) {
        BeverageType beverageType = beverageOrder.getBeverageType();
        BigDecimal moneyGiven = beverageOrder.getMoneyGiven();
        BigDecimal price = beverageType.getPrice();

        if(moneyGiven.compareTo(price) >= 0) {
            String command = beverageType.getCommandPrefix();
            if(beverageOrder.getSugarAmount() > 0) {
                command = command.concat(":" + beverageOrder.getSugarAmount() + ":0");
            } else {
                command = command.concat("::");
            }
            drinkMaker.makeDrinks(command);
            addSale(beverageType);
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

    DrinkMakerStatistics getDrinkMakerStatistics() {
        return drinkMakerStatistics;
    }

    public void addSale(BeverageType beverageType) {
        Map<BeverageType, Integer> salesMap = drinkMakerStatistics.getSalesMap();
        salesMap.put(beverageType, salesMap.get(beverageType) + 1);

        drinkMakerStatistics.setMoneyEarned(drinkMakerStatistics.getMoneyEarned().add(beverageType.getPrice()));
    }

    public void displayStatistics() {
        for(Map.Entry<BeverageType, Integer> entry : drinkMakerStatistics.getSalesMap().entrySet()) {
            System.out.println(entry.getKey().name() + ": " + entry.getValue() + " sales");
        }
        System.out.println("Total money earned: " + drinkMakerStatistics.getMoneyEarned() + " euros");
    }
}
