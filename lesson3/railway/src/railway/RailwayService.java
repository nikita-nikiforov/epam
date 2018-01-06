package railway;

import entities.Station;
import entities.Ticket;
import entities.Train;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Supplier;

import static railway.RailwayManagement.*;

public class RailwayService {

    public static void printNextTrainsOnStationWithinSixHoursAfter(String stationName,
                                                                   String timeAfter){
        printNextTrainsOnStationAfter(stationName, timeAfter, 6);
    }

    public static void printAllNextTrainsOnStation(String stationName){
        System.out.println("All trains on station " + stationName + ":");
        printNextTrainsOnStationAfter(stationName, "00:00", 24);
    }

    private static void printNextTrainsOnStationAfter(String stationName, String timeAfter,
                                                     int hoursAfter){
        Station station = getStationByName(stationName);
        if(station==null){
            System.out.println("Can't find the station.");
            return;
        } else if(!timeAfter.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")){
            System.out.println("Wrong time format.");
            return;
        }

        LocalTime parsedTimeAfter = LocalTime.parse(timeAfter);
        station.getNextTrains().forEach((train, times) -> {
            if(isBetweenTimeframe(times[1], parsedTimeAfter, hoursAfter)){
                System.out.println("Train " + train.getId() + " \"" + train.getRoute()
                        + "\". Arrival: " + times[0].toString()
                        + ", Departure: " + times[1].toString());
            }
        });
        System.out.println();
    }

    // Checks whether trainDeparture time is between specified timeAfter and timeAfter+hoursAfter
    protected static Boolean isBetweenTimeframe(LocalTime trainDeparture, LocalTime timeAfter,
                                                int hoursAfter){
        if(hoursAfter==24) return true;             // for printAllNextTrainsOnStation()
        LocalTime timeBefore = timeAfter.plusHours(hoursAfter);
        Boolean isBetweenTimeframe = ((!trainDeparture.isBefore(timeAfter)
                    && (trainDeparture.isBefore(timeBefore))));
        if(timeAfter.isAfter(timeBefore)){              // If the timeframe crosses
            return !isBetweenTimeframe;                 // midnight (i.e. 22:00 — 04:00)
        } else {
            return isBetweenTimeframe;
        }
    }

    public static void printTrainsFromTo(String fromStation, String toStation){
        Map<Train, Map<Station, LocalTime[]>> trains = getTrainsFromTo(fromStation, toStation);
        if(trains.isEmpty()) System.out.println("There's no trains from " + fromStation
                            + " to " + toStation);
        else System.out.println("All trains from " + fromStation + " to " + toStation + ":");
        trains.forEach((train, stationsAndTime) -> {
            System.out.println("-- Train " + train.getId() + " \"" + train.getRoute() + "\"");
            stationsAndTime.forEach((station, time) -> {
                System.out.println("---- " + station.getName() + ". Arrival: " + time[0].toString()
                        + ", Departure: " + time[1].toString());
            });
            System.out.println();
        });
    }

    private static Map<Train, Map<Station, LocalTime[]>> getTrainsFromTo(String fromStation, String toStation){
        Map<Train, Map<Station, LocalTime[]>> resultTrains = new TreeMap<>(); // Found trains will be located here
        Iterator<Map.Entry<String, Train>> trainIterator = trains.entrySet().iterator();
        while(trainIterator.hasNext()){
            Train train = trainIterator.next().getValue();
            Map<Station, LocalTime[]> stops = train.getStops();
            Map<Station, LocalTime[]> resultStationsAndTime = new LinkedHashMap<>(); // Temporal Map for found, firstly, arrival time and station and, secondly, departure ones
            Iterator<Map.Entry<Station, LocalTime[]>> stopsIterator = stops.entrySet().iterator();
            boolean foundFrom = false;
            boolean foundTo = false;
            while(stopsIterator.hasNext() && !foundTo){
                Map.Entry<Station, LocalTime[]> stopEntry = stopsIterator.next();
                if(!foundFrom){
                    if(stopEntry.getKey().getName().equals(fromStation)) {
                        foundFrom = true;
                        resultStationsAndTime.put(stopEntry.getKey(), stopEntry.getValue());
                    }
                } else {
                    if(stopEntry.getKey().getName().equals(toStation)){
                        foundTo = true;
                        resultStationsAndTime.put(stopEntry.getKey(), stopEntry.getValue());
                        resultTrains.put(train, resultStationsAndTime);
                    }
                }
            }
        }
        return resultTrains;
    }

    public static void buyTicket(String customerName, String trainId, int placeNumber,
                                 String startStation, String endStation){
        if(!isPlaceFree(trainId, placeNumber, startStation, endStation)){
            System.out.println("Place #" + placeNumber + " is reserved.\n");
        }else{
            Train train = getTrainById(trainId);
            List<Ticket> tickets = train.getPlaces().get(placeNumber);
            Ticket newTicket = new Ticket(customerName, train, placeNumber, startStation, endStation);
            tickets.add(newTicket);
            System.out.println("You've bought ticket" + "\nTICKET #" + newTicket.getTicketId()
                    + "\nTRAIN #" + trainId + " \"" + train.getRoute() + "\"" + "\nPLACE: "
                    + placeNumber + "\nOWNER: " + customerName + "\nFROM: " + startStation
                    + ". DEPARTURE: " + newTicket.getDepartureTime() + "\nTO: " + endStation
                    + ". ARRIVAL: " + newTicket.getArrivalTime() + "\n");
        }
    }

    protected static boolean isPlaceFree(String trainId, int placeNumber,
                                         String wantedStartStation, String wantedEndStation){
        Train train = getTrainById(trainId);
        List<Ticket> tickets = train.getPlaces().get(placeNumber);
        if(tickets.isEmpty()) return true;
        else{
            ticketloop:
            for(Ticket ticket : tickets){
                String reservedStartStation = ticket.getStartStation().getName();
                String reservedEndStation = ticket.getEndStation().getName();

                if(wantedStartStation.equals(reservedEndStation)
                        || wantedEndStation.equals(reservedStartStation)){
                    continue;
                }

                boolean foundReservedStartStation = false;
                boolean foundReservedEndStation = false;
                boolean foundWantedStartStation = false;
                boolean foundWantedEndStation = false;

                Iterator<Station> stationIterator = train.getStops().keySet().iterator();
                while(stationIterator.hasNext()){
                    String stationName = stationIterator.next().getName();

                    if(!foundWantedStartStation){
                        if(stationName.equals(wantedStartStation)) foundWantedStartStation = true;
                    }
                    if(!foundReservedStartStation){
                        if(stationName.equals(reservedStartStation)) foundReservedStartStation = true;
                    }
                    if(!foundWantedEndStation){
                        if(stationName.equals(wantedEndStation)) foundWantedEndStation = true;
                    }
                    if(!foundReservedEndStation){
                        if(stationName.equals(reservedEndStation)) foundReservedEndStation = true;
                    }

                    if(foundReservedStartStation && foundWantedStartStation
                            && !foundReservedEndStation){
                        return false;
                    } else if(foundReservedEndStation && !foundWantedStartStation){
                        continue ticketloop;
                    } else if(foundWantedEndStation && !foundReservedStartStation){
                        continue ticketloop;
                    }
                }
            }
            return true;
        }
    }
}