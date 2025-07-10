package com.trimblecars.carleaseservice.service;

import com.trimblecars.carleaseservice.dto.LeaseDTO;
import com.trimblecars.carleaseservice.exception.MaxLeaseLimitException;
import com.trimblecars.carleaseservice.exception.ResourseNotFoundException;
import com.trimblecars.carleaseservice.model.Car;
import com.trimblecars.carleaseservice.model.Lease;
import com.trimblecars.carleaseservice.repository.CarRepository;
import com.trimblecars.carleaseservice.repository.LeaseRepository;
import com.trimblecars.carleaseservice.util.MapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.http.client.reactive.ClientHttpConnectorSettings;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LeaseService {
    @Autowired
    private LeaseRepository leaseRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MapperUtil mapperUtil;

    public LeaseDTO startLease (LeaseDTO leaseDTO){
        log.info("Attempting to start lease for carId: {}, customerId: {}", leaseDTO.getCarId(), leaseDTO.getCustomerId());
        Car car = carRepository.findById(leaseDTO.getCarId()).orElseThrow(() -> new ResourseNotFoundException("Car not found with id: "+ leaseDTO.getCarId()));

        if(!"AVAILABLE".equals(car.getStatus())){
            log.warn("Customer {} has reached the maximum active lease limit ", leaseDTO.getCustomerId());
            throw new IllegalStateException("Car is not available for lease");
        }
        long activeLeases = leaseRepository.countByCustomerIdAndStatus(leaseDTO.getCustomerId(), "ACTIVE");
        if(activeLeases >=2){
            throw new MaxLeaseLimitException();
        }
        Lease lease = mapperUtil.toLease(leaseDTO);
        lease.setStartDate(LocalDateTime.now());
        lease.setStatus("ACTIVE");
        Lease saved = leaseRepository.save(lease);
        car.setStatus("LAPSED");
        carRepository.save(car);
        log.info("Lease started successfully with id: {}", saved.getId());
        return mapperUtil.toLeaseDto(saved);

    }

    public LeaseDTO endLease (Long leaseId){
        log.info("Attempting to end lease with Id:{}", leaseId);
        Lease lease = leaseRepository.findById(leaseId).orElseThrow(() -> new ResourseNotFoundException("Lease not found with id" +leaseId));
        lease.setEndDate(LocalDateTime.now());
        lease.setStatus("ENDED");
        Lease saved = leaseRepository.save(lease);

        Car car = carRepository.findById(lease.getCarId()).orElseThrow(()-> new ResourseNotFoundException("Car not found with Id:" +lease.getCarId()));
        car.setStatus("AVAILABLE");
        carRepository.save(car);
        log.info("Lease ended successfully for leaseId : {}",leaseId);
        return mapperUtil.toLeaseDto(saved);

    }

    public List<LeaseDTO> getLeaseByCustomer(Long customerId){
        log.info("Fetching lease for customerId: {}", customerId);
        return leaseRepository.findAll().stream().filter(l -> l.getCustomerId().equals(customerId)).map(mapperUtil::toLeaseDto).collect(Collectors.toList());
    }

    public List<LeaseDTO> getLeaseByCar(Long carId){
        log.info("Fetching lease for carid:{}", carId);
        return leaseRepository.findAll().stream().filter(l -> l.getCarId().equals(carId)).map(mapperUtil::toLeaseDto).collect(Collectors.toList());
    }

    public List<LeaseDTO> getAllLeases(){
        log.info("Fetching all leases");
        return leaseRepository.findAll().stream().map(mapperUtil::toLeaseDto).collect(Collectors.toList());

    }

    public LeaseDTO getLeaseById(Long id){
        log.info("Fetching lease by Id :{}",id);
        Lease lease = leaseRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("Lease not found with Id:"+id));
        return mapperUtil.toLeaseDto(lease);
    }

    public void deleteLease(Long id){
        log.info("Attempting to delete lease with id: {}",id);
        if(!leaseRepository.existsById(id)){
            log.warn("Lease with id {} not found for deletion", id);
            throw new ResourseNotFoundException("Lease not found with id: "+id);
        }
        leaseRepository.deleteById(id);
        log.info("Lease deleted with id: {}",id);
    }
}
