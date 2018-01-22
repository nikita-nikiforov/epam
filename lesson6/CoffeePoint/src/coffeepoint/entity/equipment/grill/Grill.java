package coffeepoint.entity.equipment.grill;

import coffeepoint.entity.product.food.Fastfood;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// Grill makes hotdogs and sandwiches
public class Grill {
    // Fastfood types grill can create
    private Map<String, Fastfood> fastfoodModes;

    public Grill(GrillBuilder grillBuilder){
        fastfoodModes = grillBuilder.fastfoodModes;
    }

    public static class GrillBuilder{
        private Map<String, Fastfood> fastfoodModes = new HashMap<>();

        public GrillBuilder(){}

        // Parameters are Classes' of fastfood names (i.e. "HotDog", "Sandwich")
        public GrillBuilder addFastfoodModes(String... names){
            for(String name : names){
                try {
                    Class<? extends Fastfood> fastfoodClass = (Class<? extends Fastfood>)
                            Class.forName("coffeepoint.entity.product.food.menu." + name);
                    Fastfood fastfood = fastfoodClass.newInstance();
                    Method method = fastfoodClass.getMethod("getName");
                    String nameFromMethod = (String) method.invoke(fastfood);
                    fastfoodModes.put(nameFromMethod, fastfood);
                } catch (ClassNotFoundException e) {
                    System.out.println("Don't have such fastfood class.");
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return this;
        }

        public Grill build(){
            return new Grill(this);
        }
    }

    // Create an instance of new fastfood
    public Fastfood makeFastfood(String productName){
        Fastfood fastfoodType = fastfoodModes.get(productName);
        Fastfood result = null;
        try {
             result = fastfoodType.getClass().newInstance();
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Fastfood> getFastfoodModes() {
        return fastfoodModes;
    }
}
