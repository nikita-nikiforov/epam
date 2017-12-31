package Railway;

import Entities.Station;
import Entities.Train;
import java.time.LocalTime;
import java.util.*;
import static Railway.RailwayManagement.*;

public class RailwayService {

    public static void printNextTrainsOnStation(String stationName){
        Station station = getStationByName(stationName);
        if(station!=null){
            Map<Train, LocalTime[]> nextTrains = station.getNextTrains();
            nextTrains.forEach((train, times) ->
                    System.out.println("Train " + train.getId() + " \"" + train.getRoute()
                            + "\". Arrival: " + times[0].toString()+ ", Departure: " + times[1].toString()));
        } else {
            System.out.println("Can't find a station");
        }
    }

    public static void printNextTrainsOnStationAfter(String stationName, String timeAfterString){
        Station station = getStationByName(stationName);
        LocalTime timeAfter = LocalTime.parse(timeAfterString);
        if(station!=null){
            Map<Train, LocalTime[]> nextTrains = station.getNextTrains();
            nextTrains.forEach((train, times) -> {
                if(times[1].isAfter(timeAfter) && times[1].isBefore(timeAfter.plusHours(3))){
                    System.out.println("Train " + train.getId() + " \"" + train.getRoute()
                            + "\". Arrival: " + times[0].toString()+ ", Departure: " + times[1].toString());
                }
            });
        }else {
            System.out.println("Can't find a station");
        }
    }

    public static void printTrainsFromTo(String from, String to){
        Map<Train, Map<Station, LocalTime[]>> resultTrains = new TreeMap<>();
        Iterator<Map.Entry<String, Train>> trainIterator = trains.entrySet().iterator();
        while(trainIterator.hasNext()){
            Map.Entry<String, Train> trainEntry = trainIterator.next();
            Map<Station, LocalTime[]> stops = trainEntry.getValue().getStops();
            boolean foundFrom = false;
            boolean foundTo = false;
            Map<Station, LocalTime[]> resultStationsAndTime = new LinkedHashMap<>();
            Iterator<Map.Entry<Station, LocalTime[]>> stopsIterator = stops.entrySet().iterator();
            while(stopsIterator.hasNext() && !foundTo){
                Map.Entry<Station, LocalTime[]> stopEntry = stopsIterator.next();
                if(!foundFrom){
                    if(stopEntry.getKey().getName().equalsIgnoreCase(from)) {
                        foundFrom = true;
                        resultStationsAndTime.put(stopEntry.getKey(), stopEntry.getValue());
                    }
                } else {
                    if(stopEntry.getKey().getName().equalsIgnoreCase(to)){
                        foundTo = true;
                        resultStationsAndTime.put(stopEntry.getKey(), stopEntry.getValue());
                        resultTrains.put(trainEntry.getValue(), resultStationsAndTime);
                    }
                }
            }
        }
        resultTrains.forEach((train, stationsAndTime) -> {
            System.out.println("Train " + train.getId() + " \"" + train.getRoute() + "\"");
            stationsAndTime.forEach((station, time) -> {
                System.out.println(station.getName() + ". Arrival: " + time[0].toString()
                + ", Departure: " + time[1].toString());
            });
            System.out.println();
        });
    }
}