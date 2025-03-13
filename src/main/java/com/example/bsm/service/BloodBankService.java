package com.example.bsm.service;

import com.example.bsm.response.BloodBankPageResponse;
import com.example.bsm.response.BloodBankResponse;
import com.example.bsm.entity.BloodBank;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.request.BloodBankRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BloodBankService {

    public BloodBankResponse registerBloodBank(BloodBankRequest BloodBank, int adminId);

    public Page<BloodBank> findAllBloodBanks(List<String> locations, BloodGroup bloodGroup, int page, int size);

    public BloodBankResponse findBloodBankById(int BloodBankId);

    public BloodBankResponse deleteBloodBankById(int BloodBankId);

    public BloodBankResponse updateBloodBankById(BloodBankRequest BloodBankRequest,int BloodBankId);

    public List<BloodBankPageResponse> generateBloodBankPageResponse(Page<BloodBank> bloodBanks, BloodGroup bloodGroup);


}
