package coffeepoint.entity.product.drink.additive;

public class Cream implements Additively {
    private static String name = "Cream";
    private static double price = 5.0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
