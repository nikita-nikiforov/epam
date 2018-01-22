package coffeepoint.entity.product;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.additive.Additively;
import coffeepoint.entity.product.food.Fastfood;
import coffeepoint.exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;

/* Represents the menu of CoffeePoint: which drinks, additives for coffee and food it has. */
public class Menu {
    private CoffeePoint coffeePoint;
    private Map<String, Product> drinks;
    private Map<String, Product> food;
    private Map<String, Additively> additives;

    // Initial creating in CoffeePoint
    public Menu(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
        drinks = new HashMap<>();
        food = new HashMap<>();
        additives = new HashMap<>();
    }

    // Builder of menu
    public class MenuBuilder{
        private CoffeePoint coffeePoint;
        private Map<String, Product> drinks;
        private Map<String, Product> food;
        private Map<String, Additively> additives;

        public MenuBuilder(Menu menu){
            coffeePoint = menu.coffeePoint;
            drinks = menu.drinks;
            food = menu.food;
            additives = menu.additives;
        }

        // Get names of Drink classes and check, whether CoffeeMachine can create this drinks
        public MenuBuilder addDrinks(String... names) {
            for(String name : names){
                try{
                    Class<? extends Drink> drinkClass = (Class<? extends Drink>)
                            Class.forName("coffeepoint.entity.product.drink.menu." + name);
                    String drinkName = drinkClass.newInstance().getName();
                    Drink drink = coffeePoint.getCoffeeMachine().getDrinkModes().get(drinkName);
                    if(drink!=null){
                        drinks.put(drinkName, drink);
                    } else throw new Exception();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            return this;
        }

        // Get names of Additively classes and check, whether CoffeeMachine can add these additives
        public MenuBuilder addAdditives(String... names){
            for(String name : names){
                try {
                    Class<? extends Additively> additiveClass = (Class<? extends Additively>)
                            Class.forName("coffeepoint.entity.product.drink.additive." + name);
                    String additiveName = additiveClass.newInstance().getName();
                    Additively additive = coffeePoint.getCoffeeMachine().getAdditives().get(additiveName);
                    if(additive!=null){
                        additives.put(additive.getName(), additive);
                    } else throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        // Get names of Fastfood classes and checks whether Grill can make this fastfood
        public MenuBuilder addFood(String... names){
            for(String name : names){
                try {
                    Class<? extends Fastfood> foodClass = (Class<? extends Fastfood>)
                            Class.forName("coffeepoint.entity.product.food.menu." + name);
                    String fastfoodName = foodClass.newInstance().getName();
                    Fastfood fastfood = coffeePoint.getGrill().getFastfoodModes().get(fastfoodName);
                    if(fastfood!=null){
                        food.put(fastfoodName, fastfood);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return this;
        }
    }

    // Return MenuBuilder
    public MenuBuilder getMenuBuilder(){
        return new MenuBuilder(this);
    }

    // Take in Drink name. If present, returns Product object of this Drink
    public Product getDrink(String name) throws Exception {
        Product drink = drinks.get(name);
        if(drink!=null) return drink;
        else throw new NotFoundException();
    }

    // Take in Fastfood name. If present, returns Product object of this Fastfood
    public Product getFood(String name) throws Exception{
        Product fastfood = food.get(name);
        if(fastfood!=null) return fastfood;
        else throw new NotFoundException();
    }

    // Take in additive name. If present, returns Product object of this Additively
    public Additively getAdditive(String name) throws Exception{
        Additively additive = additives.get(name);
        if(additive!=null) return additive;
        else throw new NotFoundException();
    }

}
