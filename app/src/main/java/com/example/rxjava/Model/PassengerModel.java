package com.example.rxjava.Model;

import java.util.List;

public class PassengerModel {

    private String _id;
    private String name;
    private Integer trips;
    private List<Airline> airline;
    private Integer __v;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

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

    public List<Airline> getAirline() {
        return airline;
    }

    public void setAirline(List<Airline> airline) {
        this.airline = airline;
    }

    public Integer getV() {
        return __v;
    }

    public void setV(Integer v) {
        this.__v = v;
    }

}
