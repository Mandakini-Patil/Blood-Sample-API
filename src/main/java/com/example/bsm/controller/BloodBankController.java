package com.example.bsm.controller;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.enums.BloodGroup;
import com.example.bsm.request.BloodBankRequest;
import com.example.bsm.response.BloodBankPageResponse;
import com.example.bsm.response.BloodBankResponse;
import com.example.bsm.service.BloodBankService;
import com.example.bsm.utility.PageStructure;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@AllArgsConstructor
public class BloodBankController {


    private final BloodBankService service;
    private final RestResponseBuilder responseBuilder;
    private final List<String> strings;

    @PostMapping("/bloodBanks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> registerBloodBank(@RequestBody @Valid BloodBankRequest BloodBankRequest, int adminId){
        BloodBankResponse a=service.registerBloodBank(BloodBankRequest,adminId);
        return responseBuilder.success(HttpStatus.CREATED,"BloodBank Created",a);
    }

    @GetMapping("/blood-banks")
    public ResponseEntity<PageStructure<List<BloodBankPageResponse>>> findAllBloodBanks(@RequestParam  List<String> locations, BloodGroup bloodGroup, int page, int size){
        Page<BloodBank> pages= service.findAllBloodBanks(locations,bloodGroup,page,size);
        List<BloodBankPageResponse> l=service.generateBloodBankPageResponse(pages,bloodGroup);
        return responseBuilder.success(HttpStatus.FOUND,"BloodBank Found",l,pages.getNumber(),pages.getTotalPages(),pages.getSize());
    }

    @PutMapping("/bloodBanks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> updateBloodBankById(@RequestBody @Valid BloodBankRequest BloodBankRequest,
                                                                                    int BloodBankId) {
        BloodBankResponse u=service.updateBloodBankById(BloodBankRequest,BloodBankId);
        return responseBuilder.success(HttpStatus.FOUND,"BloodBank Updated",u);
    }

    @DeleteMapping("/bloodBanks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> deleteBloodBankById(@RequestParam int BloodBankId){
        BloodBankResponse d=service.deleteBloodBankById(BloodBankId);
        return responseBuilder.success(HttpStatus.FOUND,"BloodBank Deleted",d);
    }

    @GetMapping("/bloodBank")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> findBloodBankById(@RequestParam int BloodBankId){
        BloodBankResponse f=service.findBloodBankById(BloodBankId);
        return responseBuilder.success(HttpStatus.FOUND,"BloodBank Found",f);
    }



}
