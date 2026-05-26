package com.tatdat.parking.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parking_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private ParkingZone zone;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "slot_code", nullable = false, length = 50)
    private String slotCode;

    @Column(length = 20)
    private String status;
}