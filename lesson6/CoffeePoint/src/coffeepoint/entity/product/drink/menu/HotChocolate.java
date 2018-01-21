package coffeepoint.entity.product.drink.menu;

import coffeepoint.entity.product.Bonusable;
import coffeepoint.entity.product.drink.Drink;

public class HotChocolate extends Drink implements Bonusable {
    private static String name = "Hot Chocolate";
    private static double price = 79.3;
    private static int bonus = 60;

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
