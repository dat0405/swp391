package com.tatdat.parking.backend.repository;

import com.tatdat.parking.backend.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Integer> {
}