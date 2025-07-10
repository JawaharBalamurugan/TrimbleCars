package com.trimblecars.carleaseservice.controller;

import com.trimblecars.carleaseservice.dto.LeaseDTO;
import com.trimblecars.carleaseservice.service.LeaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/leases")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @PostMapping("/start")
    public ResponseEntity<LeaseDTO> startLease(@RequestBody LeaseDTO leaseDTO){
        log.info("Starting lease for carId: {}", leaseDTO.getCarId() , leaseDTO.getCustomerId());
        LeaseDTO startedLease = leaseService.startLease(leaseDTO);
        log.info("Lease started with ID:{}", startedLease.getId());
        return ResponseEntity.ok(startedLease);
    }

    @PostMapping("/end/{leaseId}")
    public ResponseEntity<LeaseDTO> endLease (@PathVariable Long leaseId){
        log.info("Ending lease with ID:{}", leaseId);
        LeaseDTO endLease = leaseService.endLease(leaseId);
        log.info("Lease ended wit Id :{}", leaseId);
        return ResponseEntity.ok(endLease);
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<LeaseDTO>> getLeaseByCustomer(@PathVariable Long customerId){
        log.info("Fething leases for customerId: {}", customerId);
        List<LeaseDTO> leases = leaseService.getLeaseByCustomer(customerId);
        log.debug("Number of lease found for customer {} : {}", customerId, leases.size());
        return ResponseEntity.ok(leases);
    }

    @GetMapping
    public ResponseEntity<List<LeaseDTO>> getAllLeases(){
        log.info("Fetching all leases");
        List<LeaseDTO> leases = leaseService.getAllLeases();
        log.debug("Total Number of leases:{}", leases.size());
        return ResponseEntity.ok(leases);
    }
}
