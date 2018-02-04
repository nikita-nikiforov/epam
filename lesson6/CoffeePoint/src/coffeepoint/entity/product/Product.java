package coffeepoint.entity.product;

import coffeepoint.CoffeePoint;

// Abstract class for all products
public abstract class Product implements Priceable, Nameable{

    // Algorithm of how to make an instance of a product
    public abstract Product make(CoffeePoint coffeePoint);
}
