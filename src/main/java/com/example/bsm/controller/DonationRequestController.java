package com.example.bsm.controller;

import com.example.bsm.request.DonationRequestRequest;
import com.example.bsm.response.DonationRequestResponse;
import com.example.bsm.service.DonationRequestService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@AllArgsConstructor
public class DonationRequestController {
    private final DonationRequestService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/donation/hospital")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> registerDonationWithHospital(
            @RequestBody DonationRequestRequest donationRequest, @RequestParam int hospitalId) {
        DonationRequestResponse response = service.registerDonationWithHospital(donationRequest, hospitalId);
        return responseBuilder.success(HttpStatus.CREATED, "Donation Request Created for Hospital", response);
    }

    @PostMapping("/donation")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> registerDonationWithBloodBank(
            @RequestBody DonationRequestRequest donationRequest, int bloodBankId) {
        DonationRequestResponse
                response = service.registerDonationWithBloodBank(donationRequest, bloodBankId);
        return responseBuilder.success(HttpStatus.CREATED, "Donation Request Created for Blood Bank", response);
    }

    @PutMapping("/donations")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> updateDonationById(
            @RequestBody DonationRequestRequest donationRequest, @RequestParam int donationId) {
        DonationRequestResponse response = service.updateDonationById(donationRequest, donationId);
        return responseBuilder.success(HttpStatus.FOUND, "Donation Request Updated", response);
    }

    @GetMapping("/donation")
    public ResponseEntity<ResponseStructure<DonationRequestResponse>> findDonationById(@RequestParam int donationId) {
        DonationRequestResponse response = service.findDonationById(donationId);
        return responseBuilder.success(HttpStatus.FOUND, "Donation Request Found", response);
    }
}
