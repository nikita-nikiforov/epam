package coffeepoint.entity.product.drink.menu;

import coffeepoint.entity.product.drink.Coffee;
import coffeepoint.entity.product.drink.additive.Additively;
import coffeepoint.service.OrderService;

import java.util.Set;

public class CustomCoffee extends Coffee {
    private static String name = "Coffee";
    private Set<Additively> additives; // contains additives (ie. Milk, Cream, Chocolate)
    private double price = 2.5;

    public CustomCoffee() {
    }

    public CustomCoffee(CustomCoffee customCoffee){
        additives = customCoffee.additives;
        price = customCoffee.price;
    }

    // Constructor from CustomCoffeeBuilder
    public CustomCoffee(OrderService.OrderBuilder.CustomCoffeeBuilder customCoffeeBuilder){
        additives = customCoffeeBuilder.getAdditives();
        price += customCoffeeBuilder.getTotalAdditivePrice();
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

}
