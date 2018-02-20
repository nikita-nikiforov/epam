package vehicle;

import parts.engine.ElectricEngine;
import parts.engine.Engine;
import parts.transmission.AutomaticTransmission;
import parts.transmission.Transmission;

public class Tesla3 extends Vehicle {

    public Tesla3(ElectricEngine engine, AutomaticTransmission transmission) {
        super(engine, transmission);
        System.out.println("Tesla is created!");
    }

    @Override
    public void start() {
        engine.start();
        System.out.println("Vrooom!");
    }

    @Override
    public void stop() {
        engine.stop();
        System.out.println("*instant soft stoping*");
    }

    @Override
    public void changeGear() {
        transmission.changeGear();
        System.out.println("*automatic changing*");
    }
}
