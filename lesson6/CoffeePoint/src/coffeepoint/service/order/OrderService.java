package coffeepoint.service.order;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Product;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.additive.Additivable;
import coffeepoint.entity.product.drink.menu.RegularCoffee;
import coffeepoint.entity.product.food.Food;
import coffeepoint.service.bonuscard.BonusCard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderService {
    private CoffeePoint coffeePoint;

    public OrderService(CoffeePoint coffeePoint){
        this.coffeePoint = coffeePoint;
    }

//    public OrderBuilder makeOrder(){
//        return this.OrderBuilder;
//    }

    public OrderBuilder getOrderBuilder(){
        return new OrderBuilder();
    }



    public class OrderBuilder{
        OrderService orderService;
        Map<Product, Integer> products = new HashMap<>();
        double totalPrice;
        boolean paid = false;
        BonusCard bonusCard = null;

        public OrderBuilder(){
            orderService = OrderService.this;
        }

        public OrderBuilder addDrink(String name, int amount){
            Drink drink;
            try {
                drink = coffeePoint.getProductService().getDrink(name);
                products.put(drink, amount);
                totalPrice += drink.getPrice() * amount;
            } catch (Exception e) {
                System.out.println("We don't have such a drink.");
                e.printStackTrace();
            }

            return this;
        }

        public OrderBuilder addFastFood(String name, int amount){
            Food foodObject;
            try{
                foodObject = coffeePoint.getProductService().getFood(name);
                products.put(foodObject, amount);
                totalPrice += foodObject.getPrice() * amount;
            } catch(Exception e){
                System.out.println("We don't have such a food.");
                e.printStackTrace();
            }
            return this;
        }

        public CustomCoffeeBuilder addCustomCoffee(){
            return new CustomCoffeeBuilder();
        }


        public OrderBuilder useBonusCard(BonusCard bonusCard){
            this.bonusCard = bonusCard;
            return this;
        }

        public Order payWithCash(double money){
            if(money >= totalPrice){
                paid = true;
                coffeePoint.getCashbox().addMoney(totalPrice);

            }
            return build();
        }

        public Order payWithCard(int pinCode){
            if(coffeePoint.getPaymentTerminal().makeTransaction(pinCode)) {
                paid = true;
                coffeePoint.getCashbox().addMoney(totalPrice);
            }
            return build();
        }

        public Order payWithBonusCard(BonusCard bonusCard){
            this.bonusCard = bonusCard;
            if(bonusCard.getBonuses()>totalPrice){
                bonusCard.deleteBonuses((int)totalPrice);
                paid = true;
            }
            return build();
        }

        public Order build(){
            return new Order(this);
        }


        public class CustomCoffeeBuilder{
            private Set<Additivable> additives;
            private double totalAdditivePrice;

            public CustomCoffeeBuilder(){
                additives = new HashSet<>();
                totalAdditivePrice = 0;
            }

            public CustomCoffeeBuilder add(String additiveName){
                Additivable additive;
                try{
                    additive = coffeePoint.getProductService().getAdditive(additiveName);
                    additives.add(additive);
                    totalAdditivePrice += additive.getPrice();
                } catch(Exception e){
                    System.out.println("We don't have such additive.");
                    e.printStackTrace();
                }
                return this;
            }

            public OrderBuilder make(int amount){
                RegularCoffee regularCoffee = new RegularCoffee(this);
                OrderBuilder.this.products.put(regularCoffee, amount);
                return OrderBuilder.this;
            }

            public Set<Additivable> getAdditives() {
                return additives;
            }

            public double getTotalAdditivePrice() {
                return totalAdditivePrice;
            }
        }

    }


}
