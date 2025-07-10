package com.trimblecars.carleaseservice.util;

import com.trimblecars.carleaseservice.dto.CarDTO;
import com.trimblecars.carleaseservice.dto.LeaseDTO;
import com.trimblecars.carleaseservice.dto.LeaseHistoryDTO;
import com.trimblecars.carleaseservice.dto.UserDTO;
import com.trimblecars.carleaseservice.model.Car;
import com.trimblecars.carleaseservice.model.Lease;
import com.trimblecars.carleaseservice.model.User;

public class MapperUtil {

    public Car toCar(CarDTO dto){
        if (dto == null ) return null;
        Car car = new Car();
        car.setId(dto.getId());
        car.setMake(dto.getMake());
        car.setModel(dto.getModel());
        car.setCarYear(dto.getYear());
        car.setRegistrationNumber(dto.getRegistrationNumber());
        car.setStatus(dto.getStatus());
        return car;
    }

    public CarDTO toCarDTO(Car car){
        if (car == null) return null;
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setMake(car.getMake());
        dto.setModel(car.getModel());
        dto.setYear(car.getCarYear());
        dto.setRegistrationNumber(car.getRegistrationNumber());
        dto.setStatus(car.getStatus());
        return dto;
    }

    public Lease toLease (LeaseDTO dto)
    {
        if (dto == null) return null;
        Lease lease = new Lease();
        lease.setId(dto.getId());
        lease.setCarId(dto.getCustomerId());
        lease.setCustomerId(dto.getCustomerId());
        lease.setStartDate(dto.getStartDate());
        lease.setEndDate(dto.getEndDate());
        lease.setStatus(dto.getStatus());
        return lease;
    }

    public LeaseDTO toLeaseDto (Lease lease)
    {
        if(lease == null ) return null;
        LeaseDTO dto = new LeaseDTO();
        dto.setId(lease.getId());
        dto.setCarId(lease.getCarId());
        dto.setCustomerId(lease.getCustomerId());
        dto.setStartDate(lease.getStartDate());
        dto.setEndDate(lease.getEndDate());
        dto.setStatus(lease.getStatus());
        return dto;
    }

    public User toUser(UserDTO dto)
    {
        if(dto == null ) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setRole(dto.getRole());
        return user;
    }

    public UserDTO toUserDto(User user){
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRole(user.getRole());
        return dto;
    }

    public  Car updateCarFromDTO(Car car , CarDTO dto){
        if (car == null || dto == null ) return  null;
        car.setMake(dto.getMake());
        car.setModel(dto.getModel());
        car.setCarYear(dto.getYear());
        car.setRegistrationNumber(dto.getRegistrationNumber());
        car.setStatus(dto.getStatus());
        return  car;
    }

    public LeaseHistoryDTO toLeaseHistoryDTO(Lease lease){
        if (lease == null) return null;
        LeaseHistoryDTO dto = new LeaseHistoryDTO();
        dto.setLeaseId(String.valueOf(lease.getId()));
        dto.setCustomerId(String.valueOf(lease.getCustomerId()));
        dto.setCustomerName(null);
        dto.setStartDate(lease.getStartDate().toString());
        dto.setEndDate(lease.getEndDate().toString());
        dto.setStatus(lease.getStatus());
        return dto;
    }

}
