package coffeepoint.service;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Product;
import coffeepoint.exceptions.NotPaidException;
import coffeepoint.service.bonuscard.BonusCard;
import coffeepoint.service.order.Order;
import coffeepoint.service.order.OrderService.*;
import java.util.HashSet;
import java.util.Set;

/* Service for the customer, an interface */
public class CustomerService {
    private CoffeePoint coffeePoint;

    public CustomerService(CoffeePoint coffeePoint){
        this.coffeePoint = coffeePoint;
    }

    // Return OrderBuilder which permits to create an order
    public OrderBuilder getOrderBuilder(){
        return coffeePoint.getOrderService().getOrderBuilder();
    }

    // Process an order, send it to todayEmployee. Return Set of bought products
    public Set<Product> processOrder(Order order){
        Set<Product> result = new HashSet<>();      // bought products
        try {
            result = coffeePoint.getTodayEmployee().completeOrder(order);
            // add bonuses
            if(order.getBonusCard()!=null){
                int newBonuses = coffeePoint.getBonusCardService().countBonuses(result);
                order.getBonusCard().addBonuses(newBonuses);
            }
        } catch (NotPaidException e) {
            System.out.println("Order is not paid.");
            e.printStackTrace();
        }
        return result;
    }

    public BonusCard issueBonusCard(){
        return coffeePoint.getBonusCardService().createBonusCard();
    }
}
