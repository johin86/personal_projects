package com.core.drones.service.imp;

import com.core.drones.Data.request.DroneBatteryGetReq;
import com.core.drones.Data.request.DroneDeliveryReq;
import com.core.drones.Data.request.LoadDroneReq;
import com.core.drones.Data.request.RegisterDroneReq;
import com.core.drones.Data.response.*;
import com.core.drones.model.Drone;
import com.core.drones.model.MedicalDelivery;
import com.core.drones.model.Medication;
import com.core.drones.model.MedicationLoad;
import com.core.drones.repository.DroneDeliveryRepository;
import com.core.drones.repository.DroneRepository;
import com.core.drones.repository.LoadDroneRepository;
import com.core.drones.repository.MedicationRepository;
import com.core.drones.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class DroneServiceImp implements DroneService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private LoadDroneRepository loadDroneRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private DroneDeliveryRepository droneDeliveryRepository;

    @Override
    public RegisterDroneResp register(RegisterDroneReq droneRequest) {

        Drone newdrone = new Drone();
        newdrone.setSerialNumber(droneRequest.getSerialNumber());
        newdrone.setModel(droneRequest.getModel());
        newdrone.setWeightLimit(droneRequest.getWeightLimit());
        newdrone.setBattery(droneRequest.getBattery());
        newdrone.setState(droneRequest.getState());

        droneRepository.save(newdrone);

        RegisterDroneResp droneResponse = new RegisterDroneResp();
        droneResponse.setResult("success");
        droneResponse.setSerialNumber(newdrone.getSerialNumber());
        droneResponse.setMessage("New Drone created successfully");
        droneResponse.setTimestamp(java.time.LocalDateTime.now());

        return droneResponse;
    }

    @Override
    public DroneBatteryDetailsRes getBateryLevel(DroneBatteryGetReq drequest) {

        Drone newdrone = new Drone();
        DecimalFormat decFormat = new DecimalFormat("#%");
        DroneBatteryDetailsRes batteryDetailsResponse = new DroneBatteryDetailsRes();
        newdrone.setSerialNumber(drequest.getSerialNumber());
        Drone droneBattery = droneRepository.findBySerialNumber(newdrone.getSerialNumber());
        if (droneBattery == null) {
            throw new RuntimeException("Drone battery level details not found");
        }

        batteryDetailsResponse.setStatus("success");
        batteryDetailsResponse.setSerialNumber(droneBattery.getSerialNumber());
        batteryDetailsResponse.setBattery(decFormat.format(droneBattery.getBattery()));
        batteryDetailsResponse.setTimestamp(java.time.LocalDateTime.now());

        return batteryDetailsResponse;
    }

    @Override
    public DroneMedicationLoadResp getLoadMedicationForADrone(String serialno) {

        MedicationLoad loadMedication = loadDroneRepository.findByDrone(serialno);
        if(loadMedication==null) {
            throw new RuntimeException("No load Medication for this drone");
        }
        DroneMedicationLoadResp droneLoad = new DroneMedicationLoadResp();
        droneLoad.setResult("success");
        droneLoad.setSerialNumber(loadMedication.getDrone().getSerialNumber());
        droneLoad.setTimestamp(java.time.LocalDateTime.now());
        droneLoad.setMedication(loadMedication.getMedication());

        return droneLoad;
    }

    @Override
    public AvailableDroneResp getAvailabeDrones() {
        String state = "IDLE";
        List<Drone> drones = droneRepository.findAllByState(state);
        return new AvailableDroneResp("status", java.time.LocalDateTime.now(), drones);
    }

    @Override
    public LoadDroneResp loadDrone(LoadDroneReq loadRequest) {
        //preload data
        Medication medication1 = new Medication("DR111241","Covax",100,"example1");
        Medication medication2 = new Medication("DR111242","Loratadine",150,"example2");
        Medication medication3 = new Medication("DR111243","Metformin",200,"example3");
        Medication medication4 = new Medication("DR111244","Acetaminophen",300,"example4");
        Medication medication5 = new Medication("DR111245","Amoxicillin",400,"example5");
        medicationRepository.save(medication1);
        medicationRepository.save(medication2);
        medicationRepository.save(medication3);
        medicationRepository.save(medication4);
        medicationRepository.save(medication5);

        droneRepository.setUpdateState("LOADING", loadRequest.getSerialNumber());
        Drone drone = droneRepository.findBySerialNumber(loadRequest.getSerialNumber());
        Medication medication = medicationRepository.findByCode(loadRequest.getCode());
        MedicationLoad checkLoad = loadDroneRepository.findByCode(loadRequest.getCode());

        if(checkLoad != null) {
            throw new RuntimeException("Medication code has aready been loaded, try another one");

        }

        // validate before loading
        if (drone == null) {
            throw new RuntimeException("Drone specified does not exist");
        }

        if (medication == null) {
            throw new RuntimeException("Medication specified does not exist");
        }

        if (drone.getWeightLimit() < medication.getWeight()) {
            throw new RuntimeException("The Drone cannot load more than the weight limit");
        }
        // check battery
        if( drone.getBattery().compareTo(new BigDecimal(0.25)) < 0  ){
            throw new RuntimeException("Drone unavailable, battery below 25%");
        }
        // load
        MedicationLoad loadMedication = new MedicationLoad();
        loadMedication.setDrone(drone);
        loadMedication.setMedication(medication);
        loadMedication.setSource(loadRequest.getSource());
        loadMedication.setDestination(loadRequest.getDestination());
        loadMedication.setCreatedon(java.time.LocalDateTime.now());
        loadDroneRepository.save(loadMedication);
        droneRepository.setUpdateState("LOADED", loadRequest.getSerialNumber());

        LoadDroneResp loadDroneResponse = new LoadDroneResp();
        loadDroneResponse.setResult("success");
        loadDroneResponse.setSerialNumber(loadRequest.getSerialNumber());
        loadDroneResponse.setMessage("Drone Loaded successfully");
        loadDroneResponse.setTimestamp(java.time.LocalDateTime.now());

        return loadDroneResponse;
    }

    @Override
    public DeliverDroneResp deliveryLoad(DroneDeliveryReq loadRequest) {
        droneRepository.setUpdateState("DELIVERING", loadRequest.getSerialNumber());
        MedicationLoad medicationLoad = loadDroneRepository.findByDrone(loadRequest.getSerialNumber());

        if(medicationLoad==null) {
            throw new RuntimeException("Drone specifies is not loaded or does not exist");
        }

        MedicalDelivery newdelivery = new MedicalDelivery();
        newdelivery.setMedicationLoad(medicationLoad);
        newdelivery.setDeliveryTime(java.time.LocalDateTime.now());
        droneDeliveryRepository.save(newdelivery);
        droneRepository.setUpdateState("DELIVERED", loadRequest.getSerialNumber());

        DeliverDroneResp deliverDroneResponse = new DeliverDroneResp();
        deliverDroneResponse.setResult("success");
        deliverDroneResponse.setSerialNumber(loadRequest.getSerialNumber());
        deliverDroneResponse.setMessage("Delivered successfully");
        deliverDroneResponse.setTimestamp(java.time.LocalDateTime.now());

        return deliverDroneResponse;
    }

    @Override
    public void preLoadData() {
        Medication medication1 = new Medication("WE232344","Covax",100,"sade23Rd");
        Medication medication2 = new Medication("WE232345","Loratadine",150,"sade2Y4d");
        Medication medication3 = new Medication("WE232346","Metformin",200,"sade2U4d");
        Medication medication4 = new Medication("WE232347","Acetaminophen",300,"sade2Q4d");
        Medication medication5 = new Medication("WE232348","Amoxicillin",400,"sa3e234d");
        medicationRepository.save(medication1);
        medicationRepository.save(medication2);
        medicationRepository.save(medication3);
        medicationRepository.save(medication4);
        medicationRepository.save(medication5);

    }

}
