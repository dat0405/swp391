package com.tatdat.parking.backend.controller;

import com.tatdat.parking.backend.entity.ParkingZone;
import com.tatdat.parking.backend.repository.ParkingZoneRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-zones")
public class ParkingZoneController {

    private final ParkingZoneRepository parkingZoneRepository;

    public ParkingZoneController(ParkingZoneRepository parkingZoneRepository) {
        this.parkingZoneRepository = parkingZoneRepository;
    }

    // GET ALL
    @GetMapping
    public List<ParkingZone> getAllParkingZones() {
        return parkingZoneRepository.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ParkingZone getParkingZoneById(@PathVariable Integer id) {
        return parkingZoneRepository.findById(id).orElse(null);
    }

    // CREATE
    @PostMapping
    public ParkingZone createParkingZone(@RequestBody ParkingZone parkingZone) {
        return parkingZoneRepository.save(parkingZone);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ParkingZone updateParkingZone(
            @PathVariable Integer id,
            @RequestBody ParkingZone updatedZone
    ) {

        ParkingZone parkingZone =
                parkingZoneRepository.findById(id).orElse(null);

        if (parkingZone != null) {

            parkingZone.setZoneName(updatedZone.getZoneName());
            parkingZone.setFloor(updatedZone.getFloor());

            return parkingZoneRepository.save(parkingZone);
        }

        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteParkingZone(@PathVariable Integer id) {

        parkingZoneRepository.deleteById(id);

        return "Deleted Successfully";
    }
}