package parts.transmission;

public class ManualTransmission implements Transmission {

    @Override
    public void changeGear() {
        System.out.println("Changed gear with manual transmission.");
    }
}
