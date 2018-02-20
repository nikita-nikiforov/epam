package parts.transmission;

public class NonSynchronousTransmission implements Transmission {
    @Override
    public void changeGear() {
        System.out.println("Changed gear with non-synchronous transmission.");
    }
}
