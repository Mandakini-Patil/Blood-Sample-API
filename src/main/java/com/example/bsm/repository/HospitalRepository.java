package com.example.bsm.repository;

import com.example.bsm.entity.DonationRequest;
import com.example.bsm.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
    Optional<Hospital> findByDonationRequests(DonationRequest donationRequest);

}
