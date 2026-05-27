package com.tatdat.parking.backend.repository;

import com.tatdat.parking.backend.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {

    List<ParkingSlot> findByZoneId(Integer zoneId);

    List<ParkingSlot> findByStatus(String status);

    List<ParkingSlot> findByVehicleTypeIdAndStatus(Integer vehicleTypeId, String status);
}