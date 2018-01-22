package coffeepoint.service.order;

import coffeepoint.entity.product.Product;
import coffeepoint.service.bonuscard.BonusCard;
import java.util.Map;

/* Represents an order*/
public class Order {
    Map<Product, Integer> products;  // Stores types of products and amounts
    double totalPrice;
    boolean paid;               // whether customer has paid or not
    BonusCard bonusCard;        // customer's bonus card


    // Create Order from OrderBuilder
    public Order(OrderService.OrderBuilder orderBuilder){
        products = orderBuilder.products;
        totalPrice = orderBuilder.totalPrice;
        paid = orderBuilder.paid;
        bonusCard = orderBuilder.bonusCard;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public boolean isPaid() {
        return paid;
    }

    public BonusCard getBonusCard() {
        return bonusCard;
    }
}
