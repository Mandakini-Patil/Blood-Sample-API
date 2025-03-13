package com.example.bsm.repository;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Sample;
import com.example.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample,Integer> {

    List<Sample> findByBloodBankAndBloodGroupIn(BloodBank bloodBank, List<BloodGroup> bloodGroups);
}
