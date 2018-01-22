package coffeepoint.entity.employee;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Product;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.menu.CustomCoffee;
import coffeepoint.entity.product.food.Fastfood;
import coffeepoint.exceptions.NotPaidException;
import coffeepoint.entity.Order;
import java.util.*;

/* Employee of CoffeePoint*/
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

    // Complete order of a customer. Take in Order object, return Set of Products
    public Set<Product> completeOrder(Order order) throws NotPaidException {
        // throw Exception if order is not paid
        if(!order.isPaid()) throw new NotPaidException();
        Set<Product> result = new HashSet<>();
        // Map of amounts and types of products to make
        Map<Product, Integer> productsToMade = order.getProducts();
        Iterator<Map.Entry<Product, Integer>> orderProductsIterator =
                productsToMade.entrySet().iterator();
        while(orderProductsIterator.hasNext()){
            Map.Entry<Product, Integer> productEntry = orderProductsIterator.next();
            Product product = productEntry.getKey();    // get Product type
            int amount = productEntry.getValue();       // get amount
            // create [amount] instances of Product
            for(int i=0; i<amount; i++){
                if (product instanceof Drink){
                    Drink newDrink = makeDrink(product);        // for Drink
                    result.add(newDrink);
                } else if(product instanceof Fastfood){
                    Fastfood newFastfood = makeFastfood(product);   // for Fastfood
                    result.add(newFastfood);
                }
            }
        }
        return result;
    }

    // Take in Product, cast it to Drink and send to CoffeeMachine. Return Drink instance
    public Drink makeDrink(Product product){
        Drink result = null;
        // CustomCoffee has special behavior
        if(product instanceof CustomCoffee){
            result = coffeePoint.getCoffeeMachine().makeCustomCoffee((CustomCoffee) product);
        }else {
            String productName = product.getName();
            result  = coffeePoint.getCoffeeMachine().makeDrink(productName);
        }
        return result;
    }

    // Take in Product, cast it to Fastfood and send to Grill. Return Fastfood instance
    public Fastfood makeFastfood(Product product){
        String productName = product.getName();
        Fastfood resultFood = coffeePoint.getGrill().makeFastfood(productName);
        return resultFood;
    }

    public void setCoffeePoint(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
    }
}
