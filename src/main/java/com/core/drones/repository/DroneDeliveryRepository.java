package com.core.drones.repository;

import com.core.drones.model.MedicalDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneDeliveryRepository extends JpaRepository<MedicalDelivery, String> {
}
