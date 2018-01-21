package coffeepoint.entity.product;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.drink.Drink;
import coffeepoint.entity.product.drink.additive.Additivable;
import coffeepoint.entity.product.food.Food;
import coffeepoint.exceptions.NotFoundException;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private CoffeePoint coffeePoint;
    private Map<String, Product> drinks;
    private Map<String, Product> food;
    private Map<String, Additivable> additives;

    // Initial creating in CoffeePoint
    public Menu(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
        drinks = new HashMap<>();
        food = new HashMap<>();
        additives = new HashMap<>();
    }

//    public Menu(MenuBuilder builder){
//        coffeePoint = builder.coffeePoint;
//        drinks = builder.drinks;
//        food = builder.food;
//    }

    public class MenuBuilder{
        private CoffeePoint coffeePoint;
        private Map<String, Product> drinks;
        private Map<String, Product> food;
        private Map<String, Additivable> additives;

        public MenuBuilder(){
            drinks = new HashMap<>();
            food = new HashMap<>();
            additives = new HashMap<>();
        }

        public MenuBuilder(Menu menu){
            coffeePoint = menu.coffeePoint;
            drinks = menu.drinks;
            food = menu.food;
            additives = menu.additives;
        }

        // Get names of Drink classes and check, whether CoffeeMachine can make this drinks
        public MenuBuilder addDrink(String... names) {
            for(String name : names){
                try{
                    Class<? extends Drink> drinkClass = (Class<? extends Drink>)
                            Class.forName("coffeepoint.entity.product.drink.menu." + name);
                    String drinkName = drinkClass.newInstance().getName();
                    Drink drink = coffeePoint.getCoffeeMachine().getDrinkModes().get(drinkName);
                    if(drink!=null){
                        drinks.put(drink.getName(), drink);
                    } else throw new Exception();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            return this;
        }

        // Get names of Food classes and checks whether they are present
        public MenuBuilder addFood(String... names){
            for(String name : names){
                try {
                    Class<? extends Food> foodClass = (Class<? extends Food>)
                            Class.forName("coffeepoint.entity.product.food.menu." + name);
                    Food foodObject = foodClass.newInstance();
                    Method method = foodClass.getMethod("getName");
                    String nameFromMethod = (String) method.invoke(foodObject);
                    food.put(nameFromMethod, foodObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public MenuBuilder addAdditive(String... names){
            for(String name : names){
                try {
                    Class<? extends Additivable> additiveClass = (Class<? extends Additivable>)
                            Class.forName("coffeepoint.entity.product.drink.additive." + name);
                    Additivable additiveObject = additiveClass.newInstance();
                    Method method = additiveClass.getMethod("getName");
                    String nameFromMethod = (String) method.invoke(additiveObject);
                    additives.put(nameFromMethod, additiveObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

//        private <T> T createObject(String name, String classpath) throws Exception{
//            Class<? extends T> clazz = (Class<? extends T>)
//                    Class.forName(classpath + name);
//            T object = clazz.newInstance();
//            return (T) object;
//        }
    }

    public MenuBuilder getMenuBuilder(){
        return new MenuBuilder(this);
    }

    public Product getDrink(String name) throws Exception {
        Product cupOfDrink = drinks.get(name);
        if(cupOfDrink!=null) return cupOfDrink;
        else throw new NotFoundException();
    }

    public Product getFood(String name) throws Exception{
        Product foodObject = food.get(name);
        if(foodObject!=null) return foodObject;
        else throw new NotFoundException();
    }

    public Additivable getAdditive(String name) throws Exception{
        Additivable additiveObject = additives.get(name);
        if(additiveObject!=null) return additiveObject;
        else throw new NotFoundException();
    }

}
