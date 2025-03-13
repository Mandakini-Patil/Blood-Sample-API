package com.example.bsm.controller;

import com.example.bsm.request.AddressRequest;
import com.example.bsm.response.AddressResponse;
import com.example.bsm.service.AddressService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@AllArgsConstructor
public class AddressController {

    private final AddressService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/addresses/user")
    public ResponseEntity<ResponseStructure<AddressResponse>> registerUserAddress(@RequestBody @Valid AddressRequest AddressRequest) throws Exception {
        AddressResponse a=service.registerUserAddress(AddressRequest);
        return responseBuilder.success(HttpStatus.CREATED,"Address Created",a);
    }

    @PostMapping("/addresses/hospital")
    public ResponseEntity<ResponseStructure<AddressResponse>> registerHospitalAddress(@RequestBody @Valid AddressRequest AddressRequest,int hospitalId){
        AddressResponse a=service.registerHospitalAddress(AddressRequest,hospitalId);
        return responseBuilder.success(HttpStatus.CREATED,"Address Created",a);
    }

    @PostMapping("/addresses/bloodBank")
    public ResponseEntity<ResponseStructure<AddressResponse>> registerBloodBankAddress(@RequestBody AddressRequest AddressRequest,int bloodBankId){
        AddressResponse a=service.registerBllodBankAddress(AddressRequest,bloodBankId);
        return responseBuilder.success(HttpStatus.CREATED,"Address Created",a);
    }

}
