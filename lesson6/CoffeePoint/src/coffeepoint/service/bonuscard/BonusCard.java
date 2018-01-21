package coffeepoint.service.bonuscard;

public class BonusCard {
    private static int idCount = 1;
    private int id;
    private int bonuses;

    public BonusCard() {
        id = idCount++;
    }

    public void addBonuses(int bonusesAmount){
        bonuses += bonusesAmount;
    }

    public void deleteBonuses(int bonusesAmount){
        bonuses -= bonusesAmount;
    }

    public int getBonuses() {
        return bonuses;
    }

    public void setBonuses(int bonuses) {
        this.bonuses = bonuses;
    }

    public int getId() {
        return id;
    }
}
