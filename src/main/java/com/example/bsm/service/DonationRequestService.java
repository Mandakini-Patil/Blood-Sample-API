package com.example.bsm.service;

import com.example.bsm.response.DonationRequestResponse;
import com.example.bsm.request.DonationRequestRequest;

public interface DonationRequestService {


    DonationRequestResponse registerDonationWithHospital(DonationRequestRequest donationRequest, int hospitalId);


    DonationRequestResponse registerDonationWithBloodBank(DonationRequestRequest donationRequest, int bloodBankId);

    DonationRequestResponse updateDonationById(DonationRequestRequest donationRequest, int donationId);

    DonationRequestResponse findDonationById(int donationId);
}
