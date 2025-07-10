package com.trimblecars.carleaseservice.controller;

import com.trimblecars.carleaseservice.dto.CarDTO;
import com.trimblecars.carleaseservice.dto.LeaseDTO;
import com.trimblecars.carleaseservice.dto.UserDTO;
import com.trimblecars.carleaseservice.model.Lease;
import com.trimblecars.carleaseservice.service.CarService;
import com.trimblecars.carleaseservice.service.LeaseService;
import com.trimblecars.carleaseservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final CarService carService;
    private final LeaseService leaseService;


    public AdminController(UserService userService, CarService carService, LeaseService leaseService) {
        this.userService = userService;
        this.carService = carService;
        this.leaseService = leaseService;
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        log.info("Fetching all users");
        return userService.getAllUsers();
    }

    @GetMapping("/cars")
    public List<CarDTO> getAllCars(){
        log.info("Fetching all cars");
        return carService.getAllCars();
    }

    @GetMapping("leases")
    public List<LeaseDTO> getALlLeases(){
        log.info("Fetching all leases");
        return leaseService.getAllLeases();
    }

}
