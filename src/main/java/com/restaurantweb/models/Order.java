package com.restaurantweb.models;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int guestId;
    private LocalDateTime orderDatetime;
    private String orderStatus;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
