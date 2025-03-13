package com.example.bsm.service;

import com.example.bsm.response.AddressResponse;
import com.example.bsm.request.AddressRequest;
import jakarta.validation.Valid;

public interface AddressService {

    AddressResponse registerUserAddress(@Valid AddressRequest addressRequest) throws Exception;

    AddressResponse registerHospitalAddress(@Valid AddressRequest addressRequest,int hospitalId);

    AddressResponse registerBllodBankAddress(@Valid AddressRequest addressRequest,int bloodBankId);
}
