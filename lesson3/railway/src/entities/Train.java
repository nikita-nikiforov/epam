package entities;

import railway.RailwayManagement;
import java.time.LocalTime;
import java.util.*;

public class Train implements Comparable<Train>{
    private String id;                  // i.e. "705Ш"
    private String route;               // start-end points, i.e. "Lviv – Odesa"
    private Map<Station, LocalTime[]> stops = new LinkedHashMap<>(); // LocalTime[] has [0] as arrival time and [1] as departure time
    private Map<Integer, List<Ticket>> places = new HashMap<>();

    public Train(Builder builder){
        id = builder.id;
        route = builder.route;
        stops = builder.stops;
        places = builder.places;
        addTrainToStationsSchedule();
        addTrainToTrainList();
    }

    public static class Builder{
        private String id;
        private String route;
        private Map<Station, LocalTime[]> stops = new LinkedHashMap<>();
        private Map<Integer, List<Ticket>> places = new HashMap<>();

        public Builder(String id, int placesAmount){
            this.id = id;
            for(int placeNumber=1; placeNumber<=placesAmount; placeNumber++){
                places.put(placeNumber, new ArrayList<Ticket>());
            }
        }

        public Builder addStop(String stationName, String arrivalTime, String departureTime){
            Station station = RailwayManagement.getStationByName(stationName);
            if(station==null){
                System.out.println("Can't find the station" + stationName);
                // TODO
                return null;

            }
            LocalTime arrivalLocalTime = LocalTime.parse(arrivalTime);
            LocalTime departureLocalTime = LocalTime.parse(departureTime);
            LocalTime[] timeArray = {arrivalLocalTime, departureLocalTime};
            stops.put(station, timeArray);
            return this;
        }

        public void createRouteName(){
            Iterator<Map.Entry<Station, LocalTime[]>> iterator = stops.entrySet().iterator();
            String startStation = iterator.next().getKey().getName();
            String endStation = " ";
            while(iterator.hasNext()){
                endStation = iterator.next().getKey().getName();
            }
            route = startStation + " — " + endStation;
        }

        public Train build(){
            createRouteName();
            return new Train(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Station, LocalTime[]> getStops() {
        return stops;
    }

    public String getRoute() {
        return route;
    }

    public Map<Integer, List<Ticket>> getPlaces() {
        return places;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id='" + id + '\'' +
                ", stops=" + stops +
                '}';
    }

    private void addTrainToStationsSchedule(){
        stops.forEach((station, localTimes) -> {
            LocalTime[] timeArray = localTimes;
            station.nextTrains.put(this, timeArray);
        });
    }

    private void addTrainToTrainList(){
        RailwayManagement.addTrain(this);
    }

    @Override
    public int compareTo(Train train) {
        Iterator<Map.Entry<Station, LocalTime[]>> stopsIterator = stops.entrySet().iterator();
        while(stopsIterator.hasNext()){
            Station station = stopsIterator.next().getKey();
            if(train.getStops().containsKey(station)){
                if(this.getStops().get(station)[0].isBefore(train.getStops().get(station)[0])){
                    return -1;
                }
            }
        }
        return 0;
    }
}
