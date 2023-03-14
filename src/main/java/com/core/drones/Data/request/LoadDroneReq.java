package com.core.drones.Data.request;

import com.sun.istack.NotNull;

public class LoadDroneReq {

    public LoadDroneReq() {
    }

    public LoadDroneReq(String serialNumber, String source, String destination, String code) {
        this.serialNumber = serialNumber;
        this.source = source;
        this.destination = destination;
        this.code = code;
    }

    @NotNull
    private String serialNumber;

    @NotNull
    private String source;

    @NotNull
    private String destination;

    @NotNull
    private String code;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
