package coffeepoint.service.bonuscard;

/* Represents customer's bonus card */
public class BonusCard {
    private static int idCount = 1;
    private int id;             // id of card
    private int bonuses;        // amount of bonuses

    // new BonusCard with incremented id
    public BonusCard() {
        id = idCount++;
    }

    // add bonuses
    public void addBonuses(int bonusesAmount){
        bonuses += bonusesAmount;
    }

    // delete bonuses
    public void deleteBonuses(int bonusesAmount){
        bonuses -= bonusesAmount;
    }

    public int getBonuses() {
        return bonuses;
    }

    public int getId() {
        return id;
    }
}
