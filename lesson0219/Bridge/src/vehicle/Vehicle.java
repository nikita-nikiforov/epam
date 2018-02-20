package vehicle;

import parts.engine.Engine;
import parts.transmission.Transmission;

public abstract class Vehicle {
    Engine engine;
    Transmission transmission;

    public Vehicle(Engine engine, Transmission transmission) {
        this.engine = engine;
        this.transmission = transmission;
    }

    public abstract void start();
    public abstract void stop();
    public abstract void changeGear();
}
