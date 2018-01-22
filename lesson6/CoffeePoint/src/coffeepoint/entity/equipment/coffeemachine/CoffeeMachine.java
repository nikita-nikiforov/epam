package coffeepoint.entity.equipment.coffeemachine;

import coffeepoint.entity.equipment.coffeemachine.parts.*;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.additive.Additively;
import coffeepoint.entity.product.drink.menu.CustomCoffee;

import java.util.HashMap;
import java.util.Map;

/* Coffee machine. Create drinks (not only coffee, but HotChocolate too)
Знаю, что задофига составляющих (контейнеры для зёрен, молока, кофемолка
и капучинатор, лол), но позже их тоже задействую. */
public class CoffeeMachine {
    private String name;     // Name of model
    private int coffeeCupsCapacity; // How many caps of coffee can make at the same time

    /* Mandatory parameters*/
    private CoffeeGrinder coffeeGrinder;
    private CoffeeBeansContainer coffeeBeansContainer;
    private WaterContainer waterContainer;

    // Map for menu of drinks which the machine can make. Contains name of a drink and it's object
    private Map<String, Drink> drinkModes;
    // The same for additives (like Milk, Cream, Chocolate)
    private Map<String, Additively> additives;

    /* Optional parameters */
    private MilkContainer milkContainer;
    private CreamContainer creamContainer;
    private ChocolateContainer chocolateContainer;
    private Cappuccinatore cappuccinatore;

    public CoffeeMachine(Builder builder){
        name = builder.name;
        coffeeCupsCapacity = builder.coffeeCupsCapacity;
        coffeeGrinder = builder.coffeeGrinder;
        coffeeBeansContainer = builder.coffeeBeansContainer;
        waterContainer = builder.waterContainer;
        drinkModes = builder.drinkModes;
        additives = builder.additives;

        milkContainer = builder.milkContainer;
        creamContainer = builder.creamContainer;
        chocolateContainer = builder.chocolateContainer;
        cappuccinatore = builder.cappuccinatore;
    }

    // Builder for CoffeeMachine
    public static class Builder{
        // Required parameters
        private String name;
        private int coffeeCupsCapacity;
        private CoffeeGrinder coffeeGrinder;
        private CoffeeBeansContainer coffeeBeansContainer;
        private WaterContainer waterContainer;
        private Map<String, Drink> drinkModes;
        private Map<String, Additively> additives;

        // Optional parameters
        private MilkContainer milkContainer = null;
        private CreamContainer creamContainer = null;
        private ChocolateContainer chocolateContainer = null;
        private Cappuccinatore cappuccinatore = null;

            public Builder(String name, int coffeeCupsCapacity,
                           double coffeeBeansContainerCapacity,
                           double waterContainerCapacity){
                this.name = name;
                this.coffeeCupsCapacity = coffeeCupsCapacity;
                this.coffeeGrinder = new CoffeeGrinder();
                this.coffeeBeansContainer = new CoffeeBeansContainer(coffeeBeansContainerCapacity);
                this.waterContainer = new WaterContainer(waterContainerCapacity);
                this.drinkModes = new HashMap<>();
                this.additives = new HashMap<>();
                // Any coffeemachine can build regular menu
                Drink coffeeDrink = new CustomCoffee();
                drinkModes.put(coffeeDrink.getName(), coffeeDrink);
            }

            // Add container for milk
            public Builder addMilkContainer(double milkContainerCapacity){
                milkContainer = new MilkContainer(milkContainerCapacity);
                return this;
            }

            // Add container for cream
            public Builder addCreamContainer(double creamContainerCapacity){
                creamContainer = new CreamContainer(creamContainerCapacity);
                return this;
            }

            // Add container for chocolate
            public Builder addChocolateContainer(double chocolateContainerCapacity){
                chocolateContainer = new ChocolateContainer(chocolateContainerCapacity);
                return this;
            }

            // Add cappuccinatore
            public Builder addCappucinatore(){
                cappuccinatore = new Cappuccinatore();
                return this;
            }

            // Add types of Drink the machine can make.
            // Take in names of drinks' classes (i.e. "HotChocolate", "Latte")
            public Builder addDrinkModes(String... names){
                for(String name : names){
                    try {
                        Class<? extends Drink> drinkClass = (Class<? extends Drink>)
                                Class.forName("coffeepoint.entity.product.drink.menu." + name);
                        Drink drink = drinkClass.newInstance();
                        drinkModes.put(drink.getName(), drink);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Don't have such drink definition.");
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return this;
            }

            // Add types of Additives the machine can add to CustomCoffee.
            // Take in names of additives' classes (i.e. "Milk", "Cream")
            public Builder addAdditives(String... names){
                for(String name : names){
                    try {
                        Class<? extends Additively> additiveClass = (Class<? extends Additively>)
                                Class.forName("coffeepoint.entity.product.drink.additive." + name);
                        Additively additive = additiveClass.newInstance();
                        additives.put(additive.getName(), additive);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Don't have such drink definition.");
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return this;
            }

            // Build CoffeeMachine
            public CoffeeMachine build(){
                return new CoffeeMachine(this);
            }
    }

    // Take in drink name. Return instance of it
    public Drink makeDrink(String drinkName){
        Drink drink = drinkModes.get(drinkName);
        Drink newDrink = null;
        try {
            newDrink = drink.getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDrink;
    }

    // Take in constructed CustomCoffee. Return it's new instance
    public Drink makeCustomCoffee(CustomCoffee customCoffee){
        return new CustomCoffee(customCoffee);
    }

    public Map<String, Drink> getDrinkModes() {
        return drinkModes;
    }

    public Map<String, Additively> getAdditives(){
        return additives;
    }
}
