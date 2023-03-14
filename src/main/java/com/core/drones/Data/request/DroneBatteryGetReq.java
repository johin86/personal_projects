package com.core.drones.Data.request;

import com.sun.istack.NotNull;

public class DroneBatteryGetReq {

    public DroneBatteryGetReq() {
    }

    public DroneBatteryGetReq(String serialNumber) {
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
