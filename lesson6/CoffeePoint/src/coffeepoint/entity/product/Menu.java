package coffeepoint.entity.product;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.drink.additive.Additively;
import coffeepoint.exceptions.NotFoundException;
import java.util.HashMap;
import java.util.Map;

/* Represents the menu of CoffeePoint: which drinks, additives for coffee, and food
 * customer can buy. It's possible to add to menu only those products, classes of which
 * are present in the program. */
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

        // Get names of Drink classes and delegate operation to addGeneral()
        public MenuBuilder addDrinks(String... names) {
            return addGeneral("coffeepoint.entity.product.drink.menu.", drinks, names);
        }

        // Get names of Additively classes and delegate operation to addGeneral()
        public MenuBuilder addAdditives(String... names){
            return addGeneral("coffeepoint.entity.product.drink.additive.", additives, names);
        }

        // Get names of Fastfood classes and delegate operation to addGeneral()
        public MenuBuilder addFood(String... names){
            return addGeneral("coffeepoint.entity.product.food.menu.", food, names);
        }

        // Generic method for adding new element to menu
        // Takes in a path to classes of elements, a map where to add them,
        // and names of elements' classes.
        private <T extends Nameable> MenuBuilder addGeneral(String path, Map<String, T> map,
                                                            String... names){
            for(String name : names){
                try {
                    Class<? extends T> elementClass = (Class<? extends T>)
                            Class.forName(path + name);
                    T element = elementClass.newInstance();
                    if(element!=null){
                        map.put(element.getName(), element);
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