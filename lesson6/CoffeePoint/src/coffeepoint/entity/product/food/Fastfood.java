package coffeepoint.entity.product.food;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Nameable;
import coffeepoint.entity.product.Priceable;
import coffeepoint.entity.product.Product;

// Abstract class for fastfood
public abstract class Fastfood extends Product implements Priceable, Nameable {

    // Algorithm of how to make an instance of fastfood
    public Fastfood make(CoffeePoint coffeePoint){
        String productName = this.getName();
        Fastfood resultFood = coffeePoint.getGrill().makeFastfood(productName);
        return resultFood;
    }
}
