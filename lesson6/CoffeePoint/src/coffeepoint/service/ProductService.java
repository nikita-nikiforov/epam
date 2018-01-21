package coffeepoint.service;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Product;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.additive.Additivable;
import coffeepoint.entity.product.food.Food;

public class ProductService {
    private CoffeePoint coffeePoint;

    public ProductService(CoffeePoint coffeePoint){
        this.coffeePoint = coffeePoint;
    }

//    public Drink makeDrink(String name){
//
//    }

    public Drink getDrink(String name) throws Exception {
        Drink drink = (Drink) coffeePoint.getMenu().getDrink(name);
//        Drink drink = coffeePoint.getCoffeeMachine().getDrinkModes().get(name);
        if(drink!= null) return drink;
        else throw new Exception();
    }

    public Food getFood(String name) throws Exception{
        Food foodObject = (Food) coffeePoint.getMenu().getFood(name);
        if(foodObject!=null) return foodObject;
        else throw new Exception();
    }

    public Additivable getAdditive(String name) throws Exception{
        Additivable additiveObject = (Additivable) coffeePoint.getMenu().getAdditive(name);
        if(additiveObject!=null) return additiveObject;
        else throw new Exception();
    }
}
