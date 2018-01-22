package coffeepoint.service;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.Order;
import coffeepoint.entity.product.Product;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.additive.Additively;
import coffeepoint.entity.product.drink.menu.CustomCoffee;
import coffeepoint.entity.product.food.Fastfood;
import coffeepoint.entity.BonusCard;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Service for creating an order. Contains OrderBuilder. In turn, OrderBuilder contains
CustomCoffee builder, which makes coffee with additives (Milk, Chocolate, Cream) */
public class OrderService {
    private CoffeePoint coffeePoint;

    public OrderService(CoffeePoint coffeePoint){
        this.coffeePoint = coffeePoint;
    }

    // returns OrderBuilder
    public OrderBuilder getOrderBuilder(){
        return new OrderBuilder();
    }

    // Builder of Order
    public class OrderBuilder{
        private Map<Product, Integer> products = new HashMap<>();  // stores type of product and amount
        private double totalPrice;          // total price of order
        private boolean paid = false;       // whether Order paid or no
        private BonusCard bonusCard = null;     // Bonus card of customer

        // Take in the name of drink (i.e. "Cappuccino", "Hot Chocolate") and amount of cups.
        public OrderBuilder addDrink(String name, int amount){
            Drink drink;
            try {
                drink = coffeePoint.getProductService().getDrink(name); // get instance
                products.put(drink, amount);
                totalPrice += drink.getPrice() * amount;
            } catch (Exception e) {
                System.out.println("We don't have such a drink.");
                e.printStackTrace();
            }
            return this;
        }

        // Take in the name of fastFood (i.e. "Hot Dog", "Sandwich") and amount.
        public OrderBuilder addFastFood(String name, int amount){
            Fastfood foodObject;
            try{
                foodObject = coffeePoint.getProductService().getFood(name); // get instance
                products.put(foodObject, amount);
                totalPrice += foodObject.getPrice() * amount;
            } catch(Exception e){
                System.out.println("We don't have such a food.");
                e.printStackTrace();
            }
            return this;
        }

        // For creating custom coffee (with additives, ie. Milk, Cream)
        // Return CustomCoffeeBuilder (inner class)
        public CustomCoffeeBuilder addCustomCoffee(){
            return new CustomCoffeeBuilder();
        }

        // If customer wants to use its BonusCard
        public OrderBuilder useBonusCard(BonusCard bonusCard){
            this.bonusCard = bonusCard;
            return this;
        }

        // Takes in customer's money (ha-ha)
        public Order payWithCash(double money){
            if(money >= totalPrice){        // check whether it's enough money
                paid = true;
                coffeePoint.getCashbox().addMoney(totalPrice);  // add money to Cashbox

            }
            return build();
        }

        // Takes in pin-code of customer's bank card
        public Order payWithCard(int pinCode){
            if(coffeePoint.getPaymentTerminal().makeTransaction(pinCode)) { // always true
                paid = true;
                coffeePoint.getCashbox().addMoney(totalPrice); // add money to Cashbox
            }
            return build();
        }

        // Takes in customer's bonus card and buy products for bonuses
        public Order payWithBonusCard(BonusCard bonusCard){
            this.bonusCard = bonusCard;
            if(bonusCard.getBonuses()>totalPrice){          // check bonuses amount
                bonusCard.deleteBonuses((int)totalPrice); // delete bonuses from bonus card
                paid = true;
            }
            return build();
        }

        // Build order
        public Order build(){
            return new Order(this);
        }


        // Builder for custom coffee (with different additives)
        public class CustomCoffeeBuilder{
            private Set<Additively> additives;     // additives, added to coffee
            private double totalAdditivePrice;      // total price with additives

            public CustomCoffeeBuilder(){
                additives = new HashSet<>();
                totalAdditivePrice = 0;
            }

            // Takes in additive name
            public CustomCoffeeBuilder add(String additiveName){
                Additively additive;
                try{
                    // create instance of additive
                    additive = coffeePoint.getProductService().getAdditive(additiveName);
                    additives.add(additive);    // add to Set
                    totalAdditivePrice += additive.getPrice(); // add price
                } catch(Exception e){
                    System.out.println("We don't have such additive.");
                    e.printStackTrace();
                }
                return this;
            }

            // Takes in amount of cups
            // Return OrderBuilder in order to continue making order (масло масляное!)
            public OrderBuilder create(int amount){
                // instance of Coffee
                CustomCoffee customCoffee = new CustomCoffee(this);
                totalPrice += customCoffee.getPrice();      // add to Order totalPrice
                products.put(customCoffee, amount);
                return OrderBuilder.this;
            }

            public Set<Additively> getAdditives() {
                return additives;
            }

            public double getTotalAdditivePrice() {
                return totalAdditivePrice;
            }
        }

        // Getters of OrderBuilder fields. Order(OrderBuilder ob) constructor uses them
        public Map<Product, Integer> getProducts() {
            return products;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public boolean isPaid() {
            return paid;
        }

        public BonusCard getBonusCard() {
            return bonusCard;
        }
    }
}
