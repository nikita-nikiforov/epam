package coffeepoint.entity.equipment;

/* Stores money */
public class Cashbox {
    private double money;

    public void addMoney(double newMoney){
        money += newMoney;
    }

    public double getMoney() {
        return money;
    }
}
