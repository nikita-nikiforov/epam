package coffeepoint.entity.product.drink;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Product;
import coffeepoint.entity.product.drink.menu.CustomCoffee;

// Abstract class for all Drinks
public abstract class Drink extends Product {

    // Algorithm of how to make an instance of a drink
    public Drink make(CoffeePoint coffeePoint){
        Drink result = null;
        // CustomCoffee has special behavior
        if(this instanceof CustomCoffee){
            result = coffeePoint.getCoffeeMachine().makeCustomCoffee((CustomCoffee) this);
        }else {
            String productName = this.getName();
            result  = coffeePoint.getCoffeeMachine().makeDrink(productName);
        }
        return result;
    }
}