package coffeepoint.entity.equipment;

import coffeepoint.CoffeePoint;

public class Cashbox {
    private CoffeePoint coffeePoint;
    private double money;

    public Cashbox(CoffeePoint coffeePoint) {
        this.coffeePoint = coffeePoint;
        money = 0;
    }

    public void addMoney(double newMoney){
        money += newMoney;
    }

    public double getMoney() {
        return money;
    }
}
