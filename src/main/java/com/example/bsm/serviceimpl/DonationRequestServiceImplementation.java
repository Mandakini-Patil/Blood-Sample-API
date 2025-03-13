package com.example.bsm.serviceimpl;

import com.example.bsm.entity.DonationRequest;
import com.example.bsm.enums.OrganizationType;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.exception.DonationRequestNotFoundException;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.request.DonationRequestRequest;
import com.example.bsm.response.DonationRequestResponse;
import com.example.bsm.service.DonationRequestService;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.DonationRequestRepository;
import com.example.bsm.repository.HospitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DonationRequestServiceImplementation implements DonationRequestService {

    private final DonationRequestRepository donationRequestRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;

    private DonationRequestResponse mapToResponse(DonationRequest donationRequest) {
        return DonationRequestResponse.builder()
                .requestId(donationRequest.getRequestId())
                .bloodGroups(donationRequest.getBloodGroups())
                .cites(donationRequest.getCites())
                .build();

    }

    private DonationRequest mapToEntity(DonationRequestRequest request, DonationRequest donationRequest) {
        donationRequest.setCites(request.getCites());
        donationRequest.setBloodGroups(request.getBloodGroups());
        return donationRequest;
    }

    @Override
    public DonationRequestResponse registerDonationWithHospital(DonationRequestRequest request, int hospitalId) {
        var hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundByIdException("Hospital not found with ID: " + hospitalId));

        DonationRequest donationRequest = mapToEntity(request, new DonationRequest());
        donationRequest.setType(OrganizationType.HOSPITAL);
        donationRequestRepository.save(donationRequest);
        return mapToResponse(donationRequest);
    }

    @Override
    public DonationRequestResponse registerDonationWithBloodBank(DonationRequestRequest request, int bloodBankId) {
        var bloodBank = bloodBankRepository.findById(bloodBankId)
                .orElseThrow(() -> new BloodBankNotFoundByIdException("Blood bank not found with ID: " + bloodBankId));
        var donationRequest = mapToEntity(request, new DonationRequest());
        donationRequest.setType(OrganizationType.BLOODBANK);
        donationRequestRepository.save(donationRequest);
        return mapToResponse(donationRequest);
    }

    @Override
    public DonationRequestResponse updateDonationById(DonationRequestRequest request, int donationId) {
        var donationRequest = donationRequestRepository.findById(donationId)
                .orElseThrow(() -> new DonationRequestNotFoundException("Donation request not found with ID: " + donationId));
        mapToEntity(request, donationRequest);
        donationRequestRepository.save(donationRequest);
        return mapToResponse(donationRequest);
    }

    @Override
    public DonationRequestResponse findDonationById(int donationId) {
        var donationRequest = donationRequestRepository.findById(donationId)
                .orElseThrow(() -> new DonationRequestNotFoundException("Donation request not found with ID: " + donationId));
        return mapToResponse(donationRequest);
    }
}