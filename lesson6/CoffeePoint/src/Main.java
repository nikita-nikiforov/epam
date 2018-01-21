import coffeepoint.CoffeePoint;
import coffeepoint.entity.equipment.PaymentTerminal;
import coffeepoint.entity.equipment.grill.Grill;
import coffeepoint.entity.product.Product;
import coffeepoint.service.bonuscard.BonusCard;
import coffeepoint.service.bonuscard.BonusCardService;
import coffeepoint.service.order.Order;
import coffeepoint.service.ManagementService;
import coffeepoint.service.CustomerService;
import coffeepoint.entity.employee.Employee;
import coffeepoint.entity.equipment.coffeemachine.CoffeeMachine;

import java.util.Set;

public class Main {

    public static void main(String[] args) {

        CoffeePoint coffeePoint = new CoffeePoint();
        ManagementService managementService = coffeePoint.getManagement();
        CustomerService customerService = coffeePoint.getCustomerService();
        BonusCardService bonusCardService = coffeePoint.getBonusCardService();

        CoffeeMachine philipsCoffeeMachine = new CoffeeMachine.Builder("Philips 420", 2, 250, 2000)
                .addMilkContainer(500).addChocolateContainer(250)
                .addCreamContainer(250).addCappucinatore()
                .addDrinkModes("Latte", "HotChocolate", "Cappuccino", "Americano", "Espresso")
                .addAdditives("Milk", "Chocolate", "Cream").build();
        coffeePoint.setCoffeeMachine(philipsCoffeeMachine);

        Grill grill = new Grill.GrillBuilder().addFastfoodModes("HotDog", "Sandwich").build();
        coffeePoint.setGrill(grill);

        coffeePoint.getMenu().getMenuBuilder().addDrink("Latte", "HotChocolate", "Cappuccino")
                    .addFood("HotDog").addAdditive("Milk", "Chocolate", "Cream");


        managementService.hireEmployee(new Employee("Name", "Surname", 3200));
        managementService.setTodayEmployee();
        coffeePoint.open();

        BonusCard customerBonusCard = bonusCardService.createBonusCard();

        Order order = customerService.getOrderBuilder().addDrink("Latte", 1)
                .addDrink("Hot Chocolate", 2).addFastFood("Hot Dog", 1)
                .useBonusCard(customerBonusCard).payWithCash(301);
        Set<Product> purchasedProducts = customerService.makeOrder(order);

        Order order2 = customerService.getOrderBuilder().addDrink("Latte", 3)
                .payWithBonusCard(customerBonusCard);
        Set<Product> purchasedProducts2 = customerService.makeOrder(order2);


        Order order3 = customerService.getOrderBuilder().addCustomCoffee().add("Milk").make(3).payWithCash(78987);
        Set<Product> purchasedProducts3 = customerService.makeOrder(order3);
        System.out.println(coffeePoint.getCashbox().getMoney());


    }
}
