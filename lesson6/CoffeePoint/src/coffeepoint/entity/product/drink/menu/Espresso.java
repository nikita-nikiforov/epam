package coffeepoint.entity.product.drink.menu;

import coffeepoint.entity.product.drink.Coffee;
import coffeepoint.entity.product.drink.GrindingLevel;

public class Espresso extends Coffee {
    private static String name = "Espresso";
    private static double price = 11.0;

    GrindingLevel grindingLevel = GrindingLevel.MIN;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
