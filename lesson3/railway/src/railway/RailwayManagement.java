package railway;

import entities.Station;
import entities.Train;
import java.util.HashMap;
import java.util.Map;

public class RailwayManagement {
    protected static Map<String, Station> stations = new HashMap<>();
    protected static Map<String, Train> trains = new HashMap<>();


    public static void addStations(String... names){
        for(String name : names){
            stations.put(name, new Station(name));
        }
    }

    public static void printAllStations(){
        stations.forEach((stationName, station) -> System.out.print(stationName + ", "));
        System.out.println();
    }

    // Look for a station with given name. If can't find, return null
    public static Station getStationByName(String name){
        if(stations.containsKey(name)) return stations.get(name);
        else return null;
    }

    public static void addTrain(Train train){
        trains.put(train.getId(), train);
    }
}
