package coffeepoint.entity.product.food.menu;

import coffeepoint.entity.product.food.Fastfood;

public class Sandwich extends Fastfood{
    private static String name = "Sandwich";
    private static double price = 22.0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
