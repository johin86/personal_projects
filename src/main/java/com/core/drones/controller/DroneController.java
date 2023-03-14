package com.core.drones.controller;


import com.core.drones.Data.request.DroneBatteryGetReq;
import com.core.drones.Data.request.DroneDeliveryReq;
import com.core.drones.Data.request.LoadDroneReq;
import com.core.drones.Data.request.RegisterDroneReq;
import com.core.drones.Data.response.*;
import com.core.drones.service.imp.DroneServiceImp;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/drone")
@Validated
public class DroneController {

    @Autowired
    private DroneServiceImp droneService;

    @PostMapping(path="/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegisterDroneResp> registerDrone(
            @NotNull @RequestBody RegisterDroneReq dronerequest) {
        RegisterDroneResp newDrone = droneService.register(dronerequest);
        return new ResponseEntity<RegisterDroneResp>(newDrone, HttpStatus.CREATED);
    }


    @PostMapping(path ="/battery", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DroneBatteryDetailsRes> checkDroneBattery(
            @NotNull @RequestBody(required = true) DroneBatteryGetReq drequest) {
        if (drequest.getSerialNumber() == null || drequest.getSerialNumber().isEmpty()) {
            throw new RuntimeException("SerialNumber is Required");
        }
        DroneBatteryDetailsRes droneBattery = droneService.getBateryLevel(drequest);
        return new ResponseEntity<DroneBatteryDetailsRes>(droneBattery, HttpStatus.CREATED);
    }

    @PostMapping(path = "/load", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoadDroneResp> loadDroneWithMedication(@NotNull @RequestBody LoadDroneReq loadrequest) {
        LoadDroneResp loadDrone = droneService.loadDrone(loadrequest);
        return new ResponseEntity<LoadDroneResp>(loadDrone, HttpStatus.CREATED);
    }

    @GetMapping(path= "/available", produces = "application/json")
    public ResponseEntity<AvailableDroneResp> getAvailableDroneForLoading() {
        AvailableDroneResp drones = droneService.getAvailabeDrones();
        return new ResponseEntity<AvailableDroneResp>(drones, HttpStatus.OK);
    }

    @GetMapping(path = "details/{serialNumber}", produces = "application/json")
    public ResponseEntity<DroneMedicationLoadResp> checkLoadedMedicationItem(@PathVariable("serialNumber") String serialNumber) {
        DroneMedicationLoadResp droneLoads = droneService.getLoadMedicationForADrone(serialNumber);
        return new ResponseEntity<DroneMedicationLoadResp>(droneLoads, HttpStatus.OK);
    }

    @PostMapping(path = "/deliver", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DeliverDroneResp> droneMedicalLoadDelivery(@NotNull @RequestBody DroneDeliveryReq deliverRequest) {
        DeliverDroneResp delivery = droneService.deliveryLoad(deliverRequest);
        return new ResponseEntity<DeliverDroneResp>(delivery, HttpStatus.CREATED);
    }
}
