package railway;

import entities.Station;
import entities.Train;
import java.time.LocalTime;
import java.util.*;
import static railway.RailwayManagement.*;

public class RailwayService {

    public static void printNextTrainsOnStationWithinSixHoursAfter(String stationName,
                                                                   String timeAfter){
        printNextTrainsOnStationAfter(stationName, timeAfter, 6);
    }

    public static void printAllNextTrainsOnStation(String stationName){
        printNextTrainsOnStationAfter(stationName, "00:00", 24);
    }

    private static void printNextTrainsOnStationAfter(String stationName, String timeAfterStr,
                                                     int hoursAfter){
        Station station = getStationByName(stationName);
        if(station==null){
            System.out.println("Can't find the station.");
            return;
        } else if(!timeAfterStr.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")){
            System.out.println("Wrong time format.");
            return;
        }
        LocalTime timeAfter = LocalTime.parse(timeAfterStr);
        station.getNextTrains().forEach((train, times) -> {
            if(isBetweenTimeframe(times[1], timeAfter, hoursAfter) || hoursAfter==24){ //
                System.out.println("Train " + train.getId() + " \"" + train.getRoute()
                        + "\". Arrival: " + times[0].toString()
                        + ", Departure: " + times[1].toString());
            }
        });
    }

    // Checks whether trainDeparture time is between specified timeAfter and timeAfter + 6 hours
    protected static Boolean isBetweenTimeframe(LocalTime trainDeparture, LocalTime timeAfter,
                                                int hoursAfter){
        Boolean isBetweenTimeframe = ((!trainDeparture.isBefore(timeAfter)
                    && (trainDeparture.isBefore(timeAfter.plusHours(hoursAfter)))));
        if(timeAfter.isAfter(timeAfter.plusHours(hoursAfter))){
            return !isBetweenTimeframe;  // If the timeframe crosses midnight (i.e. 22:00 — 04:00)
        } else {
            return isBetweenTimeframe;
        }
    }

    public static void printTrainsFromTo(String fromStation, String toStation){
        Map<Train, Map<Station, LocalTime[]>> resultTrains = new TreeMap<>();
        Iterator<Map.Entry<String, Train>> trainIterator = trains.entrySet().iterator();
        while(trainIterator.hasNext()){
            Train train = trainIterator.next().getValue();
            Map<Station, LocalTime[]> stops = train.getStops();
            boolean foundFrom = false;
            boolean foundTo = false;
            Map<Station, LocalTime[]> resultStationsAndTime = new LinkedHashMap<>();
            Iterator<Map.Entry<Station, LocalTime[]>> stopsIterator = stops.entrySet().iterator();
            while(stopsIterator.hasNext() && !foundTo){
                Map.Entry<Station, LocalTime[]> stopEntry = stopsIterator.next();
                if(!foundFrom){
                    if(stopEntry.getKey().getName().equalsIgnoreCase(fromStation)) {
                        foundFrom = true;
                        resultStationsAndTime.put(stopEntry.getKey(), stopEntry.getValue());
                    }
                } else {
                    if(stopEntry.getKey().getName().equalsIgnoreCase(toStation)){
                        foundTo = true;
                        resultStationsAndTime.put(stopEntry.getKey(), stopEntry.getValue());
                        resultTrains.put(train, resultStationsAndTime);
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