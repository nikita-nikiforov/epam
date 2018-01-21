package coffeepoint.entity.product.drink.menu;

import coffeepoint.entity.product.drink.Coffee;
import coffeepoint.entity.product.drink.additive.Additivable;
import coffeepoint.service.order.OrderService;

import java.util.HashSet;
import java.util.Set;

public class RegularCoffee extends Coffee {
    private static String name = "Regular Coffee";
    private Set<Additivable> additives;
    private double price = 2.5;

    public RegularCoffee() {
    }

    public RegularCoffee(RegularCoffee regularCoffee){
        additives = regularCoffee.additives;
        price = regularCoffee.price;
    }

    public RegularCoffee(OrderService.OrderBuilder.CustomCoffeeBuilder customCoffeeBuilder){
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
