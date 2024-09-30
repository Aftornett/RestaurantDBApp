package com.restaurantweb.models;

public class Guest {
    private int id;
    private String name;
    private String phoneNumber;
    private String deliveryAdr;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeliveryAdr() {
        return deliveryAdr;
    }

    public void setDeliveryAdr(String deliveryAdr) {
        this.deliveryAdr = deliveryAdr;
    }
}
