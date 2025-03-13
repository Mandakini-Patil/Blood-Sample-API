package com.example.bsm.service;

import com.example.bsm.response.HospitalResponse;
import com.example.bsm.request.HospitalRequest;

import java.util.List;

public interface HospitalService {
    public HospitalResponse registerHospital(HospitalRequest Hospital, int adminId);

    public List<HospitalResponse> findAllHospitals();

    public HospitalResponse findHospitalById(int HospitalId);

    public HospitalResponse deleteHospitalById(int HospitalId);

    public HospitalResponse updateHospitalById(HospitalRequest HospitalRequest,int HospitalId);


}
