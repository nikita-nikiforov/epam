package coffeepoint.entity.product.drink.menu;

import coffeepoint.entity.product.Bonusable;
import coffeepoint.entity.product.drink.Coffee;

public class Cappuccino extends Coffee implements Bonusable {
    private static String name = "Cappuccino";
    private static double price = 25.9;
    private static int bonus =  15;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getBonusValue() {
        return bonus;
    }
}
