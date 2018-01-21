package coffeepoint.service.bonuscard;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.product.Bonusable;
import coffeepoint.entity.product.Product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BonusCardService {
    private CoffeePoint coffeePoint;
    Map<Integer, BonusCard> bonusCards = new HashMap<>();


    public BonusCardService(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
    }

    public int countBonuses(Set<Product> boughtProducts){
        int result = 0;
        Iterator<Product> boughtProductsIterator = boughtProducts.iterator();
        while(boughtProductsIterator.hasNext()){
            Product product = boughtProductsIterator.next();
            if(product instanceof Bonusable){
                Bonusable bonusableProduct = (Bonusable) product;
                result += bonusableProduct.getBonusValue();
            }
        }
        return result;
    }

    public Map<Integer, BonusCard> getBonusCards() {
        return bonusCards;
    }

    public BonusCard createBonusCard(){
        BonusCard bonusCard = new BonusCard();
        bonusCards.put(bonusCard.getId(), bonusCard);
        return bonusCard;
    }

}
