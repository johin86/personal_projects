package com.core.drones.Data.response;

import com.core.drones.model.Medication;

import java.time.LocalDateTime;

public class DroneMedicationLoadResp {

    public DroneMedicationLoadResp() {
    }

    public DroneMedicationLoadResp(String result, String serialNumber, LocalDateTime timestamp, Medication medication) {
        this.result = result;
        this.serialNumber = serialNumber;
        this.timestamp = timestamp;
        this.medication = medication;
    }

    private String result;
    private String serialNumber;
    private LocalDateTime timestamp;
    Medication medication;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }
}
