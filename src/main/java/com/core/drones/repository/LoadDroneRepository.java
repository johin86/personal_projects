package com.core.drones.repository;

import com.core.drones.model.MedicationLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoadDroneRepository extends JpaRepository<MedicationLoad, String> {

    @Query(value = "SELECT * from drone_load e where e.fk_serial_no =:serialno ", nativeQuery = true)
    MedicationLoad findByDrone(@Param("serialno") String serialno);

    @Query(value = "SELECT e from MedicationLoad e where e.medication.code =:code ")
    MedicationLoad findByCode(@Param("code") String code);

}
