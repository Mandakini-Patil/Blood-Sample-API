package com.example.bsm.repository;

import com.example.bsm.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationLeadRepository extends JpaRepository<Address,Integer> {
}
