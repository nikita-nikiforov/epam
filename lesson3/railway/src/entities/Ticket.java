package entities;

import railway.RailwayManagement;

import java.time.LocalTime;

public class Ticket {
    static int idOfLastBoughtTicket = 0;

    private String ticketId;
    private String customerName;
    private Train train;
    private Integer placeNumber;
    private Station startStation;
    private Station endStation;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public Ticket(String customerName, Train train, Integer placeNumber, String startStation,
                  String endStation) {
        idOfLastBoughtTicket++;
        this.ticketId = String.format("%09d", idOfLastBoughtTicket); // 9-width string, filled with zeros in the begining (ie. "000000168")
        this.customerName = customerName;
        this.train = train;
        this.placeNumber = placeNumber;
        this.startStation = RailwayManagement.getStationByName(startStation);
        this.endStation = RailwayManagement.getStationByName(endStation);
        this.departureTime = train.getStops().get(this.startStation)[1];
        this.arrivalTime = train.getStops().get(this.endStation)[0];
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Integer getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(Integer placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
