package coffeepoint.service.order;

import coffeepoint.entity.product.Product;
import coffeepoint.service.bonuscard.BonusCard;

import java.util.Map;

public class Order {
    Map<Product, Integer> products;
    double totalPrice;
    boolean paid;
    BonusCard bonusCard;


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
