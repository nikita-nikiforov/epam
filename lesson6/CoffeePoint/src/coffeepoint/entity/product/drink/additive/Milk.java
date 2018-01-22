package coffeepoint.entity.product.drink.additive;

public class Milk implements Additively {
    private static String name = "Milk";
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
