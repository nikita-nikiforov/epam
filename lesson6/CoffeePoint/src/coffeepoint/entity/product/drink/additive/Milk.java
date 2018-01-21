package coffeepoint.entity.product.drink.additive;

public class Milk implements Additivable {
    private static String name = "Milk";
    private static double price = 3.0;

    public Milk() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
