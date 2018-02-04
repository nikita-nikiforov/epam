import coffeepoint.CoffeePoint;
import coffeepoint.entity.equipment.grill.Grill;
import coffeepoint.entity.product.Product;
import coffeepoint.entity.BonusCard;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.menu.Latte;
import coffeepoint.service.BonusCardService;
import coffeepoint.entity.Order;
import coffeepoint.service.ManagementService;
import coffeepoint.service.CustomerService;
import coffeepoint.entity.employee.Employee;
import coffeepoint.entity.equipment.coffeemachine.CoffeeMachine;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // Create new CoffeePoint
        CoffeePoint coffeePoint = new CoffeePoint();

        // Services from CoffeePoint
        ManagementService managementService = coffeePoint.getManagement();
        CustomerService customerService = coffeePoint.getCustomerService();
        BonusCardService bonusCardService = coffeePoint.getBonusCardService();

        // Create new coffee machine
        CoffeeMachine philipsCoffeeMachine = new CoffeeMachine.Builder("Philips 420", 2, 250, 2000)
                .addMilkContainer(500).addChocolateContainer(250)
                .addCreamContainer(250).addCappucinatore()
                .addDrinkModes("Latte", "HotChocolate", "Cappuccino", "Americano", "Espresso")
                .addAdditives("Milk", "Chocolate", "Cream").build();

        // Set this coffee machine to coffeePoint
        coffeePoint.setCoffeeMachine(philipsCoffeeMachine);

        // Create new grill device
        Grill grill = new Grill.GrillBuilder().addFastfoodModes("HotDog", "Sandwich").build();

        // Set this grill to coffeePoint
        coffeePoint.setGrill(grill);

        // Set menu in coffeePoint
        coffeePoint.getMenu().getMenuBuilder()
                .addDrinks("Latte", "HotChocolate", "Cappuccino", "Americano", "Espresso")
                .addFood("HotDog", "Sandwich")
                .addAdditives("Milk", "Chocolate", "Cream");

        // Hire new Employee
        managementService.hireEmployee(new Employee("Name", "Surname", 3200));

        // Open coffeePoint
        coffeePoint.open();

        // Now I'm a customer. I want to issue my own BonusCard
        BonusCard customerBonusCard = customerService.issueBonusCard();

        // Make new order, use bonus card and pay with cash
        Order order = customerService.getOrderBuilder().addDrink("Latte", 1)
                .addDrink("Hot Chocolate", 2).addFastFood("Hot Dog", 1)
                .useBonusCard(customerBonusCard).payWithCash(301);
        // Process order and get Set of my products!
        Set<Product> purchasedProducts = customerService.processOrder(order);

        // New order, now pay with bonuses from my bonus card
        Order order2 = customerService.getOrderBuilder().addDrink("Latte", 3)
                .payWithBonusCard(customerBonusCard);
        // Process order and get my products
        Set<Product> purchasedProducts2 = customerService.processOrder(order2);

        // Another order. Now I want buy 3 Coffees with Milk and Chocolate
        Order order3 = customerService.getOrderBuilder()
                .addCustomCoffee().add("Milk").add("Chocolate").create(3).payWithCash(78987);
        // Process order and get my products
        Set<Product> purchasedProducts3 = customerService.processOrder(order3);


        System.out.println("CoffeePoint earned " + coffeePoint.getCashbox().getMoney()
                        + " dollars!"); // CoffeePoint earned 213.57 dollars!

//        Latte latte = new Latte();
//        Drink newLatte = latte.make();
//        System.out.println(newLatte + " " + newLatte.make());
    }
}
