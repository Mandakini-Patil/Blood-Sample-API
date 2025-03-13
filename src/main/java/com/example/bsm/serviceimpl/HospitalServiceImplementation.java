package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Address;
import com.example.bsm.entity.Admin;
import com.example.bsm.entity.Hospital;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.request.HospitalRequest;
import com.example.bsm.response.HospitalResponse;
import com.example.bsm.service.HospitalService;

import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.AddressRepository;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HospitalServiceImplementation implements HospitalService {


    private final HospitalRepository repository;
    private final AdminRepository adminRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    private HospitalResponse mapToHospitalRes(Hospital hospital) {
        return HospitalResponse.builder()
                .hospitalId(hospital.getHospitalId())
                .hospitalName(hospital.getHospitalName())


                .build();
    }

    private  Hospital mapToHospitalReq(HospitalRequest hospitalRequest, Hospital hospital, Address address) {

        hospital.setHospitalName(hospitalRequest.getHospitalName());


        addressRepository.save(address);
        return hospital;
    }


    @Override
    public HospitalResponse registerHospital(HospitalRequest HospitalRequest,int adminId) {
        Optional<Admin> optional=adminRepository.findById(adminId);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("failed to find Admin");
        }

        Hospital hospital=new Hospital();

        hospital= this.mapToHospitalReq(HospitalRequest,hospital,new Address());


        hospital=repository.save(hospital);

        return this.mapToHospitalRes(hospital);
    }



    @Override
    public HospitalResponse findHospitalById(int HospitalId) {
        Optional<Hospital> optional=repository.findById(HospitalId);
        if (optional.isEmpty()){
            throw new HospitalNotFoundByIdException("Failed to find Hospital");
        }
        Hospital Hospital=optional.get();
        return mapToHospitalRes(Hospital);
    }

    @Override
    public List<HospitalResponse> findAllHospitals()
    {
        List<Hospital> l=repository.findAll();
        if (l.isEmpty()){
            throw new HospitalNotFoundByIdException("failed to find blood bank");
        }
        List<HospitalResponse> res=new ArrayList<>();
        for(Hospital hospital:l)
        {
            res.add(this.mapToHospitalRes(hospital));
        }
        return res;
    }



    @Override
    public HospitalResponse deleteHospitalById(int HospitalId) {
        Optional<Hospital> optional=repository.findById(HospitalId);
        if(optional.isPresent()) {
            Hospital a=optional.get();
            repository.delete(a);
            return this.mapToHospitalRes(a);
        }else {
            throw new RuntimeException("Failed to delete Actor");
        }
    }

    @Override
    public HospitalResponse updateHospitalById(HospitalRequest HospitalRequest,int HospitalId) {
        Optional<Hospital> optional=repository.findById(HospitalId);
        if (optional.isEmpty()){
            throw new HospitalNotFoundByIdException("Failed to update");
        }
        Hospital hospital=optional.get();


        return this.mapToHospitalRes(hospital);
    }




}
