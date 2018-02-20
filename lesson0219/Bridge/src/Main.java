import parts.engine.ElectricEngine;
import parts.engine.HeatEngine;
import parts.transmission.AutomaticTransmission;
import parts.transmission.ManualTransmission;
import vehicle.Tesla3;
import vehicle.VAZ2105;

public class Main {

    public static void main(String[] args) {
        System.out.println("----- Our participants are preparing for the race.\n");
        Tesla3 tesla3 = new Tesla3(new ElectricEngine(), new AutomaticTransmission());
        VAZ2105 vaz2105 = new VAZ2105(new HeatEngine(), new ManualTransmission());

        System.out.println("\n----- The race begins!\n");

        System.out.print("Tesla: ");
        tesla3.start();
        System.out.print("VAZ-2105: ");
        vaz2105.start();

        System.out.println("\n----- Oh yeah! VAZ-2105 is the leader!\n");

        System.out.print("Tesla: ");
        tesla3.changeGear();
        System.out.print("VAZ-2105: ");

        vaz2105.changeGear();

        System.out.println("\n----- Not for a long. Tesla gets ahead!\n");

        System.out.print("Tesla: ");
        tesla3.stop();
        System.out.print("VAZ-2105: ");
        vaz2105.stop();

        System.out.println("\n----- Aaaaaaand, Tesla 3 wins. Congratulations, Elon Musk!");
    }
}
