package com.ironhack.midtermproject.classes;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String streetName;
    private int numberAddress;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String streetName, int numberAddress, String city, String country) {
        this.streetName = streetName;
        this.numberAddress = numberAddress;
        this.city = city;
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getNumberAddress() {
        return numberAddress;
    }

    public void setNumberAddress(int numberAddress) {
        this.numberAddress = numberAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
