package coffeepoint.entity.equipment;

import coffeepoint.CoffeePoint;

public class PaymentTerminal {
    CoffeePoint coffeePoint;

    public PaymentTerminal(CoffeePoint coffeePoint){
        this.coffeePoint = coffeePoint;
    }

    public boolean makeTransaction(int pinCode){
        // ...bank operations
        //
        return true;
    }
}
