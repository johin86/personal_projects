package com.core.drones.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_delivery")
public class MedicalDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "delivery_time", columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime deliveryTime;

    @OneToOne(targetEntity = MedicationLoad.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_trackingid", referencedColumnName = "trackingid")
    private MedicationLoad medicationLoad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public MedicationLoad getMedicationLoad() {
        return medicationLoad;
    }

    public void setMedicationLoad(MedicationLoad medicationLoad) {
        this.medicationLoad = medicationLoad;
    }
}
