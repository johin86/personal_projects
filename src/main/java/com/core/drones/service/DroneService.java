package com.core.drones.service;

import com.core.drones.Data.request.DroneBatteryGetReq;
import com.core.drones.Data.request.DroneDeliveryReq;
import com.core.drones.Data.request.LoadDroneReq;
import com.core.drones.Data.request.RegisterDroneReq;
import com.core.drones.Data.response.*;
import org.springframework.stereotype.Component;

@Component
public interface DroneService {

    RegisterDroneResp register(RegisterDroneReq droneRequest);
    DroneBatteryDetailsRes getBateryLevel(DroneBatteryGetReq drequest) throws Exception;
    DroneMedicationLoadResp getLoadMedicationForADrone(String serialno);
    AvailableDroneResp getAvailabeDrones();
    LoadDroneResp loadDrone(LoadDroneReq loadRequest);
    DeliverDroneResp deliveryLoad(DroneDeliveryReq loadRequest);
    void preLoadData();


}
