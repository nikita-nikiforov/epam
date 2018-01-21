package coffeepoint.entity.employee;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Product;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.menu.RegularCoffee;
import coffeepoint.entity.product.food.Fastfood;
import coffeepoint.entity.product.food.Food;
import coffeepoint.exceptions.NotPaidException;
import coffeepoint.service.order.Order;

import java.util.*;

public class Employee {
    private static int idCount = 1;
    private int id;
    private String firstName;
    private String secondName;
    private double salary;
    private CoffeePoint coffeePoint;

    public Employee(String firstName, String secondName, double salary) {
        this.id = idCount++;
        this.firstName = firstName;
        this.secondName = secondName;
        this.salary = salary;
    }

    public Set<Product> completeOrder(Order order) throws NotPaidException {
        if(!order.isPaid()) throw new NotPaidException();
        Set<Product> result = new HashSet<>();
        Map<Product, Integer> productsToMade = order.getProducts();
        Iterator<Map.Entry<Product, Integer>> orderProductsIterator =
                productsToMade.entrySet().iterator();
        while(orderProductsIterator.hasNext()){
            Map.Entry<Product, Integer> productEntry = orderProductsIterator.next();
            Product product = productEntry.getKey();
            int amount = productEntry.getValue();
            for(int i=0; i<amount; i++){
                if (product instanceof Drink){
                    Drink newDrink = makeDrink(product);
                    result.add(newDrink);
                } else if(product instanceof Fastfood){
                    Food newFastfood = makeFastfood(product);
                    result.add(newFastfood);
                }
            }
        }
        return result;
    }

    // Cast Product parameter to Drink and create new instance
    public Drink makeDrink(Product product){
        Drink result = null;
        // RegularCoffee has special behavior
        if(product instanceof RegularCoffee){
            result = coffeePoint.getCoffeeMachine().makeCustomCoffee((RegularCoffee) product);
        }else {
            String productName = product.getName();
            result  = coffeePoint.getCoffeeMachine().makeDrink(productName);
        }
        return result;
    }

    // Cast Product parameter to Food and create new instance
    public Food makeFastfood(Product product){
        String productName = product.getName();
        Food resultFood = coffeePoint.getGrill().makeFastfood(productName);
        return resultFood;
    }




    public void setCoffeePoint(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
    }
}
