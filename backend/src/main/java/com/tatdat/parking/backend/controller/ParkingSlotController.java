package com.tatdat.parking.backend.controller;

import com.tatdat.parking.backend.entity.ParkingSlot;
import com.tatdat.parking.backend.repository.ParkingSlotRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-slots")
public class ParkingSlotController {

    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingSlotController(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @GetMapping
    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotRepository.findAll();
    }

    @GetMapping("/zone/{zoneId}")
    public List<ParkingSlot> getSlotsByZone(@PathVariable Integer zoneId) {
        return parkingSlotRepository.findByZoneId(zoneId);
    }

    @GetMapping("/available")
    public List<ParkingSlot> getAvailableSlots() {
        return parkingSlotRepository.findByStatus("AVAILABLE");
    }

    @GetMapping("/available/vehicle-type/{vehicleTypeId}")
    public List<ParkingSlot> getAvailableSlotsByVehicleType(@PathVariable Integer vehicleTypeId) {
        return parkingSlotRepository.findByVehicleTypeIdAndStatus(vehicleTypeId, "AVAILABLE");
    }
}