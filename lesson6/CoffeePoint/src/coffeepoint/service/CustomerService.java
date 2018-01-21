package coffeepoint.service;


import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Product;
import coffeepoint.exceptions.NotPaidException;
import coffeepoint.service.order.Order;
import coffeepoint.service.order.OrderService.*;

import java.util.HashSet;
import java.util.Set;

public class CustomerService {
    private CoffeePoint coffeePoint;

    public CustomerService(CoffeePoint coffeePoint){
        this.coffeePoint = coffeePoint;

    }

    public OrderBuilder getOrderBuilder(){
        return coffeePoint.getOrderService().getOrderBuilder();
    }

    public Set<Product> makeOrder(Order order){
        Set<Product> result = new HashSet<>();
        try {
            result = coffeePoint.getTodayEmployee().completeOrder(order);
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

}
