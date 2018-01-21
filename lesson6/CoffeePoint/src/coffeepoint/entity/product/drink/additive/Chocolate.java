package coffeepoint.entity.product.drink.additive;

public class Chocolate implements Additivable {
    private static String name = "Chocolate";
    private static double price = 3.0;


    public Chocolate() {
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
