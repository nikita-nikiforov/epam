package coffeepoint.entity.product.drink.menu;

import coffeepoint.entity.product.Bonusable;
import coffeepoint.entity.product.drink.Coffee;
import coffeepoint.entity.product.drink.Milky;

public class Latte extends Coffee implements Bonusable {
    private static String name = "Latte";
    private static double price = 17.17;
    private static int bonus = 3;

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


//    public String getName(){
//        return name;
//    }
}
