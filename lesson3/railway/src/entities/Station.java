package entities;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Station {
    private String name;
    protected Map<Train, LocalTime[]> nextTrains = new TreeMap<>(); // TODO treemap

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<Train, LocalTime[]> getNextTrains() {
        return nextTrains;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                '}';
    }
}
