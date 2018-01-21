package coffeepoint.entity.equipment.coffeemachine;

import coffeepoint.entity.equipment.coffeemachine.parts.*;
import coffeepoint.entity.product.Product;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.additive.Additivable;
import coffeepoint.entity.product.drink.menu.RegularCoffee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {
    /* Mandatory parameters*/
    // Name of model
    private String name;
    // How many caps of menu can build at the same time
    private int coffeeCupsCapacity;
    private CoffeeGrinder coffeeGrinder;
    private CoffeeBeansContainer coffeeBeansContainer;
    private WaterContainer waterContainer;
    // Map for menu of drinks whick a machine can build. Contains name of a drink and it's object
    private Map<String, Drink> drinkModes;
    private Map<String, Additivable> additives;
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

    public static class Builder{
        // Required parameters
        private String name;
        private int coffeeCupsCapacity;
        private CoffeeGrinder coffeeGrinder;
        private CoffeeBeansContainer coffeeBeansContainer;
        private WaterContainer waterContainer;
        private Map<String, Drink> drinkModes;
        private Map<String, Additivable> additives;


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
                Drink coffeeDrink = new RegularCoffee();
                drinkModes.put(coffeeDrink.getName(), coffeeDrink);
            }

            public Builder addMilkContainer(double milkContainerCapacity){
                milkContainer = new MilkContainer(milkContainerCapacity);
                return this;
            }

            public Builder addCreamContainer(double creamContainerCapacity){
                creamContainer = new CreamContainer(creamContainerCapacity);
                return this;
            }

            public Builder addChocolateContainer(double chocolateContainerCapacity){
                chocolateContainer = new ChocolateContainer(chocolateContainerCapacity);
                return this;
            }

            public Builder addCappucinatore(){
                cappuccinatore = new Cappuccinatore();
                return this;
            }

            // Parameters are names of drinks' classes (i.e. "HotChocolate", "Latte")
            public Builder addDrinkModes(String... names){
                for(String name : names){
                    try {
                        Class<? extends Drink> drinkClass = (Class<? extends Drink>)
                                Class.forName("coffeepoint.entity.product.drink.menu." + name);
                        Drink drink = drinkClass.newInstance();
                        Method method = drinkClass.getMethod("getName");
                        String nameFromMethod = (String) method.invoke(drink);
                        drinkModes.put(nameFromMethod, drink);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Don't have such drink definition.");
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return this;
            }

            public Builder addAdditives(String... names){
                for(String name : names){
                    try {
                        Class<? extends Additivable> additiveClass = (Class<? extends Additivable>)
                                Class.forName("coffeepoint.entity.product.drink.additive." + name);
                        Additivable additive = additiveClass.newInstance();
                        Method method = additiveClass.getMethod("getName");
                        String nameFromMethod = (String) method.invoke(additive);
                        additives.put(nameFromMethod, additive);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Don't have such drink definition.");
                        e.printStackTrace();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return this;
            }

            public CoffeeMachine build(){
                return new CoffeeMachine(this);
            }
    }

    public Drink makeDrink(String drinkName){
        Drink drink = drinkModes.get(drinkName);
        Drink newDrink = null;
        // Немножко говнокода из-за конструктора RegularCoffee. Делает копию RegularCoffee со всеми
        // добавками (Milk, Cream..)
        // TODO
        if(drink instanceof RegularCoffee) newDrink = new RegularCoffee((RegularCoffee) drink);
        else{
            try {
                newDrink = drink.getClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newDrink;
    }

    public Drink makeCustomCoffee(RegularCoffee regularCoffee){
        return new RegularCoffee(regularCoffee);
    }

    public Map<String, Drink> getDrinkModes() {
        return drinkModes;
    }

    public Map<String, Additivable> getAdditives(){
        return additives;
    }
}
