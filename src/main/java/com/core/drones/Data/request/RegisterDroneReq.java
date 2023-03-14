package com.core.drones.Data.request;

import com.sun.istack.NotNull;

import java.math.BigDecimal;

public class RegisterDroneReq {

    public RegisterDroneReq() {

    }

    public RegisterDroneReq(String serialNumber, String model, double weightLimit, BigDecimal battery, String state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.battery = battery;
        this.state = state;
    }

    @NotNull
    private String serialNumber;

    @NotNull
    private String model;

    @NotNull
    private double weightLimit;

    @NotNull
    private BigDecimal battery;

    @NotNull
    private String state;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public BigDecimal getBattery() {
        return battery;
    }

    public void setBattery(BigDecimal battery) {
        this.battery = battery;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
