public class OrderTranslator {

    private DrinkMaker drinkMaker;

    public boolean translateOrder(BeverageOrder beverageOrder) {
        String command = switch(beverageOrder.getBeverageType()) {
            case COFFEE -> "C";
            case TEA -> "T";
            case CHOCOLATE -> "H";
            default -> null;
        };
        if(command == null) {
            return false;
        }
        if(beverageOrder.getSugarAmount() > 0) {
            command = command.concat(":" + beverageOrder.getSugarAmount());
            command = command.concat(":" + (beverageOrder.isHasStick() ? 1 : 0));
        } else {
            command = command.concat("::");
        }
        drinkMaker.makeDrinks(command);
        return true;
    }

    public boolean sendMessage(String message) {
        String command = "M:" + message;
        drinkMaker.makeDrinks(command);
        return true;
    }
}
