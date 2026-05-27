package com.tatdat.parking.backend.repository;

import com.tatdat.parking.backend.entity.ParkingFloor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Integer> {
}