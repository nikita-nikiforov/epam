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
        // Map of types and amounts of products to make
        Map<Product, Integer> productsToMake = order.getProducts();
        Iterator<Map.Entry<Product, Integer>> productsToMakeIterator =
                productsToMake.entrySet().iterator();
        while(productsToMakeIterator.hasNext()){
            Map.Entry<Product, Integer> productEntry = productsToMakeIterator.next();
            Product product = productEntry.getKey();    // get Product type
            int amount = productEntry.getValue();       // get amount
            // create [amount] instances of Product
            for(int i=0; i<amount; i++){
                result.add(makeProduct(product));
            }
        }
        return result;
    }

    // Delegates how to make one instance of product to Product class
    public Product makeProduct(Product product){
        return product.make(coffeePoint);
    }

    public void setCoffeePoint(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
    }
}