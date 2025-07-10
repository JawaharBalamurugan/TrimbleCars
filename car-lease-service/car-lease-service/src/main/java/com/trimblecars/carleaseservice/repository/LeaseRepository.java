package com.trimblecars.carleaseservice.repository;

import com.trimblecars.carleaseservice.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {
    long countByCustomerIdAndStatus(Long customerId,String active);
    List<Lease> findByCarId (Long carId);
}
