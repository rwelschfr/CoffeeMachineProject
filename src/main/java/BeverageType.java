public enum BeverageType {
    TEA(0.4),
    COFFEE(0.5),
    CHOCOLATE(0.6);

    private double price;

    BeverageType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
