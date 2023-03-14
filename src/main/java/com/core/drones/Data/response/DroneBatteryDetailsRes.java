package com.core.drones.Data.response;

import java.time.LocalDateTime;

public class DroneBatteryDetailsRes {


    public DroneBatteryDetailsRes() {
    }

    public DroneBatteryDetailsRes(String status, String serialNumber, String battery, LocalDateTime timestamp) {
        this.status = status;
        this.serialNumber = serialNumber;
        this.battery = battery;
        this.timestamp = timestamp;
    }

    private String status;
    private String serialNumber;
    private String battery;
    private LocalDateTime timestamp;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
