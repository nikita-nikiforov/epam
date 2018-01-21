package coffeepoint.entity.product.drink.menu;

import coffeepoint.entity.product.drink.Coffee;
import coffeepoint.entity.product.drink.GrindingLevel;

public class Americano extends Coffee {
    private static String name = "Americano";
    private static double price = 31.5;

    GrindingLevel grindingLevel = GrindingLevel.MAX;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
