package coffeepoint.service;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.additive.Additively;
import coffeepoint.entity.product.food.Fastfood;
import coffeepoint.exceptions.NotFoundException;

/* Service for getting products from menu */
public class ProductService {
    private CoffeePoint coffeePoint;

    public ProductService(CoffeePoint coffeePoint){
        this.coffeePoint = coffeePoint;
    }

    public Drink getDrink(String name) throws Exception {
        Drink drink = (Drink) coffeePoint.getMenu().getDrink(name);
        if(drink!= null) return drink;
        else throw new NotFoundException();
    }

    public Fastfood getFood(String name) throws Exception{
        Fastfood foodObject = (Fastfood) coffeePoint.getMenu().getFood(name);
        if(foodObject!=null) return foodObject;
        else throw new NotFoundException();
    }

    public Additively getAdditive(String name) throws Exception{
        Additively additiveObject = coffeePoint.getMenu().getAdditive(name);
        if(additiveObject!=null) return additiveObject;
        else throw new NotFoundException();
    }
}
