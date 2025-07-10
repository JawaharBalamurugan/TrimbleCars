package com.trimblecars.carleaseservice.service;

import com.trimblecars.carleaseservice.dto.CarDTO;
import com.trimblecars.carleaseservice.dto.LeaseHistoryDTO;
import com.trimblecars.carleaseservice.exception.ResourseNotFoundException;
import com.trimblecars.carleaseservice.model.Car;
import com.trimblecars.carleaseservice.model.Lease;
import com.trimblecars.carleaseservice.repository.CarRepository;
import com.trimblecars.carleaseservice.repository.LeaseRepository;
import com.trimblecars.carleaseservice.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private MapperUtil mapperUtil;

    public CarDTO registerCar(CarDTO carDTO){
        log.debug("Registering car: {}", carDTO);
        Car car = mapperUtil.toCar(carDTO);
        car.setStatus("AVAILABLE");
        Car saved = carRepository.save(car);
        return mapperUtil.toCarDTO(saved);
    }

    public List<CarDTO> getAllCars(){
        log.debug("Getting all cars");
        return carRepository.findAll().stream().map(mapperUtil::toCarDTO).collect(Collectors.toList());
    }

    public CarDTO getCarById(Long id){
        log.debug("Getting car by ID:{}", id);
        Car car = carRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("Car not found with id: "+id));
        return mapperUtil.toCarDTO(car);
    }

    public List<CarDTO> getCarsByOwner(Long ownerId){
        log.debug("Getting cars by owner ID: {}",ownerId);
        return  carRepository.findByOwnerId(ownerId).stream().map(mapperUtil::toCarDTO).collect(Collectors.toList());
    }

    public  CarDTO updateCar(Long id , CarDTO carDTO){
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Car not found with id:"+id));
        car = mapperUtil.updateCarFromDTO(car, carDTO);
        Car updated = carRepository.save(car);
        return mapperUtil.toCarDTO(updated);

    }

    public  void deleteCar(Long id){
        if(!carRepository.existsById(id)){
            throw new ResourseNotFoundException("Car not found with id: "+id);
        }
        carRepository.deleteById(id);
    }

    public List<LeaseHistoryDTO> getLeaseHistory(Long carId){
        log.debug("Getting lease history for Car ID: {}",carId);
        List<Lease> leases = leaseRepository.findByCarId(carId);
        return leases.stream().map(mapperUtil::toLeaseHistoryDTO).collect(Collectors.toList());

    }

}
