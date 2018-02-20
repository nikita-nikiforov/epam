package parts.engine;

public class HeatEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Started with heat engine.");
    }

    @Override
    public void stop() {
        System.out.println("Stoped with heat engine.");
    }
}
