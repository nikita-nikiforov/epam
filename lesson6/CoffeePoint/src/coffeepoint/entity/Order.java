package coffeepoint.entity;

import coffeepoint.entity.product.Product;
import coffeepoint.service.OrderService;

import java.util.Map;

/* Represents an order*/
public class Order {
    private Map<Product, Integer> products;  // Stores types of products and amounts
    private double totalPrice;
    private boolean paid;               // whether customer has paid or not
    private BonusCard bonusCard;        // customer's bonus card


    // Create Order from OrderBuilder
    public Order(OrderService.OrderBuilder orderBuilder){
        products = orderBuilder.getProducts();
        totalPrice = orderBuilder.getTotalPrice();
        paid = orderBuilder.isPaid();
        bonusCard = orderBuilder.getBonusCard();
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
