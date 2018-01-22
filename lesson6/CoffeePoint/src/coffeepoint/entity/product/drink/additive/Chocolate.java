package coffeepoint.entity.product.drink.additive;

public class Chocolate implements Additively {
    private static String name = "Chocolate";
    private static double price = 3.0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
