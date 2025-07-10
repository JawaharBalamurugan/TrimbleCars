package com.trimblecars.carleaseservice.service;

import com.trimblecars.carleaseservice.dto.CarDTO;
import com.trimblecars.carleaseservice.dto.LeaseDTO;
import com.trimblecars.carleaseservice.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService {
    @Autowired
    private CarService carService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private UserService userService;

    public List<UserDTO> getAllUsers(){
        log.info("Fetching all users");
        return userService.getAllUsers();
    }

    public UserDTO getUserById(Long id){
        log.info("Fetching user by id :{}", id);
        return userService.getUserById(id);
    }

    public void deleteUser(Long id){
        log.info("Deleting user by ID : {}",id);
        userService.deleteUser(id);
    }

    public List<CarDTO> getAllCars(){
        log.info("Fetching all cars");
        return carService.getAllCars();
    }

    public CarDTO getCarById(Long id){
        log.info("Fetching car by id: {}",id);
        return carService.getCarById(id);
    }

    public CarDTO addCar(CarDTO carDTO){
        log.info("Adding new car : {}", carDTO);
        return carService.registerCar(carDTO);
    }

    public CarDTO updateCar(Long id , CarDTO carDTO){
        log.info("updating car with ID: {}, Data:{}", id, carDTO);
        return carService.updateCar(id, carDTO);
    }

    public void deleteCar(Long id){
        log.info("Deleting car by ID: {}",id);
        carService.deleteCar(id);
    }

    public LeaseDTO getLeaseById(Long id){
        log.info("Fetching lease by ID: {}" ,id);
        return leaseService.getLeaseById(id);
    }

    public LeaseDTO startLease (LeaseDTO leaseDTO){
        log.info("Starting new lease: {}", leaseDTO);
        return leaseService.startLease(leaseDTO);
    }

    public LeaseDTO endLease(Long leaseId){
        log.info("Ending lease with ID: {}",leaseId);
        return leaseService.endLease(leaseId);
    }

    public void deleteLease(Long id){
        log.info("Deleting lease by ID: {}", id);
        leaseService.deleteLease(id);
    }


}
