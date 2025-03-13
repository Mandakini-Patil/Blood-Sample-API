package com.example.bsm.serviceimpl;

import com.example.bsm.entity.BloodBank;
import com.example.bsm.entity.Sample;
import com.example.bsm.exception.BloodBankNotFoundByIdException;
import com.example.bsm.request.SampleRequest;
import com.example.bsm.response.SampleResponse;
import com.example.bsm.service.SampleService;
import com.example.bsm.exception.SampleNotFoundByIdException;
import com.example.bsm.repository.BloodBankRepository;
import com.example.bsm.repository.SampleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SampleServiceImplementation implements SampleService {



    private final SampleRepository repository;
    private final BloodBankRepository bloodBankRepository;

    private SampleResponse mapToSampleRes(Sample sample) {
        return SampleResponse.builder()
                .sampleId(sample.getSampleId())
                .bloodGroup(sample.getBloodGroup())
                .quantity(sample.getQuantity())


                .build();
    }

    private  Sample mapToSampleReq(SampleRequest SampleRequest, Sample sample) {
        sample.setBloodGroup(SampleRequest.getBloodGroup());
        sample.setQuantity(SampleRequest.getQuantity());
        if(SampleRequest.getQuantity()<sample.getEmergencyUnit()){
            sample.setEmergencyUnit(SampleRequest.getQuantity());
            return sample;
        } else if (SampleRequest.getQuantity()==sample.getEmergencyUnit()) {
            return sample;
        }else {
            sample.setAvailableUnits(SampleRequest.getQuantity()-sample.getEmergencyUnit());
            return sample;
        }

    }


    @Override
    public SampleResponse registerSample(SampleRequest SampleRequest,int bloodBankId) {
        Optional<BloodBank> optional=bloodBankRepository.findById(bloodBankId);
        if (optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("failed to find Admin");
        }
        BloodBank BloodBank=optional.get();
        Sample sample=new Sample();
        sample.setEmergencyUnit(BloodBank.getEmergencyUnitUnitCount());
        sample=this.mapToSampleReq(SampleRequest,sample);

        sample= this.mapToSampleReq(SampleRequest,new Sample());
        BloodBank=bloodBankRepository.save(BloodBank);
        sample.setBloodBank(BloodBank);
        sample=repository.save(sample);
        return this.mapToSampleRes(sample);
    }

    @Override
    public SampleResponse findSampleById(int SampleId) {
        Optional<Sample> optional=repository.findById(SampleId);
        if (optional.isEmpty()){
            throw new SampleNotFoundByIdException("Failed to find Sample");
        }
        Sample Sample=optional.get();
        return mapToSampleRes(Sample);
    }

    @Override
    public List<SampleResponse> findAllSamples()
    {
        List<Sample> l=repository.findAll();
        if (l.isEmpty()){
            throw new SampleNotFoundByIdException("failed to find blood bank");
        }
        List<SampleResponse> res=new ArrayList<>();
        for(Sample Sample:l)
        {
            res.add(this.mapToSampleRes(Sample));
        }
        return res;
    }



    @Override
    public SampleResponse deleteSampleById(int SampleId) {
        Optional<Sample> optional=repository.findById(SampleId);
        if(optional.isPresent()) {
            Sample a=optional.get();
            repository.delete(a);
            return this.mapToSampleRes(a);
        }else {
            throw new RuntimeException("Failed to delete Actor");
        }
    }

    @Override
    public SampleResponse updateSampleById(SampleRequest SampleRequest,int SampleId) {
        Optional<Sample> optional=repository.findById(SampleId);
        if (optional.isEmpty()){
            throw new SampleNotFoundByIdException("Failed to update");
        }
        Sample Sample=this.mapToSampleReq(SampleRequest,optional.get());
        return this.mapToSampleRes(Sample);
    }









}
