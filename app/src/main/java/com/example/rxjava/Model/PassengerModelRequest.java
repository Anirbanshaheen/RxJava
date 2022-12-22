package com.example.rxjava.Model;

public class PassengerModelRequest {
    private String name;
    private Integer trips;
    private Integer airline;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTrips() {
        return trips;
    }

    public void setTrips(Integer trips) {
        this.trips = trips;
    }

    public Integer getAirline() {
        return airline;
    }

    public void setAirline(Integer airline) {
        this.airline = airline;
    }
}
