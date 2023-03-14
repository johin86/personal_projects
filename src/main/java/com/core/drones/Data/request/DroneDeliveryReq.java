package com.core.drones.Data.request;

import com.sun.istack.NotNull;

public class DroneDeliveryReq {

    public DroneDeliveryReq() {
    }

    public DroneDeliveryReq(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @NotNull
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
