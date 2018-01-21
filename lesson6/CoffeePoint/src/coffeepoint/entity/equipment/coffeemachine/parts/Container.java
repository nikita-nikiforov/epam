package coffeepoint.entity.equipment.coffeemachine.parts;

public abstract class Container {
    private double capacity;

    Container(double capacity){
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }
}
