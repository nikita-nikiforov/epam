package coffeepoint.entity.product.food.menu;

import coffeepoint.entity.product.food.Fastfood;

public class HotDog extends Fastfood{
    private static String name = "Hot Dog";
    private static double price = 29.3;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
