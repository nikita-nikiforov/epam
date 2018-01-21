package coffeepoint.entity.product.drink.menu;

import coffeepoint.entity.product.drink.Coffee;
import coffeepoint.entity.product.drink.Milky;

public class Cappuccino extends Coffee implements Milky {
    private static String name = "Cappuccino";
    private static double price = 25.9;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
