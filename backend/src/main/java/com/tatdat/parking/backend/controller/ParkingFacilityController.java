package com.tatdat.parking.backend.controller;

import com.tatdat.parking.backend.entity.ParkingFacility;
import com.tatdat.parking.backend.repository.ParkingFacilityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-facilities")
public class ParkingFacilityController {

    private final ParkingFacilityRepository parkingFacilityRepository;

    public ParkingFacilityController(ParkingFacilityRepository parkingFacilityRepository) {
        this.parkingFacilityRepository = parkingFacilityRepository;
    }

    @GetMapping
    public List<ParkingFacility> getAllParkingFacilities() {
        return parkingFacilityRepository.findAll();
    }
}