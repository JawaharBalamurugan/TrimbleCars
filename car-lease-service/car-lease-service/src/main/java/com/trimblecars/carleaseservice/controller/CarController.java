package com.trimblecars.carleaseservice.controller;

import com.trimblecars.carleaseservice.dto.CarDTO;
import com.trimblecars.carleaseservice.dto.LeaseHistoryDTO;
import com.trimblecars.carleaseservice.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<CarDTO> registerCar(@RequestBody CarDTO carDTO){
        log.info("Fetching all cars");
        CarDTO createdCar = carService.registerCar(carDTO);
        log.info("Car registered with Id: {}", createdCar.getId() );
        return ResponseEntity.ok(createdCar);

    }

    @GetMapping("/{/id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
        log.info("Fetching car by id :{}", id);
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<CarDTO>> getCarByOwner(@PathVariable Long ownerId){
        log.info("Fetching cars for owner ID : {}", ownerId);
        return ResponseEntity.ok(carService.getCarsByOwner(ownerId));
    }

    @GetMapping("/{id}/lease-history")
    public ResponseEntity<List<LeaseHistoryDTO>> getLeaseHistory(@PathVariable Long id){
        log.info("Fetching lease history for car ID : {}", id);
        return ResponseEntity.ok(carService.getLeaseHistory(id));
    }










}
