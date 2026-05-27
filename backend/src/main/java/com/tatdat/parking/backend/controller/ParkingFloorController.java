package com.tatdat.parking.backend.controller;

import com.tatdat.parking.backend.entity.ParkingFloor;
import com.tatdat.parking.backend.repository.ParkingFloorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-floors")
public class ParkingFloorController {

    private final ParkingFloorRepository parkingFloorRepository;

    public ParkingFloorController(ParkingFloorRepository parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }

    @GetMapping
    public List<ParkingFloor> getAllParkingFloors() {
        return parkingFloorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ParkingFloor getParkingFloorById(@PathVariable Integer id) {
        return parkingFloorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ParkingFloor createParkingFloor(@RequestBody ParkingFloor parkingFloor) {
        return parkingFloorRepository.save(parkingFloor);
    }

    @PutMapping("/{id}")
    public ParkingFloor updateParkingFloor(
            @PathVariable Integer id,
            @RequestBody ParkingFloor updatedFloor
    ) {
        ParkingFloor parkingFloor =
                parkingFloorRepository.findById(id).orElse(null);

        if (parkingFloor != null) {
            parkingFloor.setFloorName(updatedFloor.getFloorName());
            parkingFloor.setFacility(updatedFloor.getFacility());

            return parkingFloorRepository.save(parkingFloor);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteParkingFloor(@PathVariable Integer id) {
        parkingFloorRepository.deleteById(id);
        return "Deleted Successfully";
    }
}