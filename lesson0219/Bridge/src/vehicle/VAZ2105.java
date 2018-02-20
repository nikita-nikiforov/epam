package vehicle;

import parts.engine.Engine;
import parts.engine.HeatEngine;
import parts.transmission.ManualTransmission;
import parts.transmission.Transmission;

public class VAZ2105 extends Vehicle {

    public VAZ2105(HeatEngine engine, ManualTransmission transmission) {
        super(engine, transmission);
        System.out.println("Пятёрочка готова!");
    }

    @Override
    public void start() {
        engine.start();
        System.out.println("Брррррр.");
    }

    @Override
    public void stop() {
        engine.stop();
        System.out.println("*отваливается передний бампер*");
    }

    @Override
    public void changeGear() {
        transmission.changeGear();
        System.out.println("*странные звуки*");
    }
}
