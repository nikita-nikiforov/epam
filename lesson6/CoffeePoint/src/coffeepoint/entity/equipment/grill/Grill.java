package coffeepoint.entity.equipment.grill;

import coffeepoint.entity.product.food.Fastfood;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Grill {

    private Map<String, Fastfood> fastfoodModes;

    public Grill(GrillBuilder grillBuilder){
        fastfoodModes = grillBuilder.fastfoodModes;
    }

    public static class GrillBuilder{
        private Map<String, Fastfood> fastfoodModes = new HashMap<>();


        public GrillBuilder(){}

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
                    System.out.println("Don't have such fastfood definition.");
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

}
