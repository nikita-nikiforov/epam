package coffeepoint.service;

import coffeepoint.CoffeePoint;
import coffeepoint.entity.BonusCard;
import coffeepoint.entity.product.Bonusable;
import coffeepoint.entity.product.Product;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* Service for BonusCard actions */
public class BonusCardService {
    private CoffeePoint coffeePoint;
    Map<Integer, BonusCard> bonusCards = new HashMap<>();   // all existed BonusCards

    public BonusCardService(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
    }

    // Count bonuses for all Products bought by customer. Return their sum
    public int countBonuses(Set<Product> boughtProducts){
        int result = 0;
        Iterator<Product> boughtProductsIterator = boughtProducts.iterator();
        while(boughtProductsIterator.hasNext()){    // iterate through all Products
            Product product = boughtProductsIterator.next();
            if(product instanceof Bonusable){       // check whether Product is Bonusable
                Bonusable bonusableProduct = (Bonusable) product;
                result += bonusableProduct.getBonusValue();     // add bonuses to result
            }
        }
        return result;
    }

    // Create new BonusCard for Customer
    public BonusCard createBonusCard(){
        BonusCard bonusCard = new BonusCard();
        bonusCards.put(bonusCard.getId(), bonusCard);
        return bonusCard;
    }

    public Map<Integer, BonusCard> getBonusCards() {
        return bonusCards;
    }
}
