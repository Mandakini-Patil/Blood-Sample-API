package com.example.bsm.mappper;

import com.example.bsm.request.SampleRequest;
import com.example.bsm.response.SampleResponse;
import com.example.bsm.entity.Sample;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SampleMapper {

    public SampleResponse mapToSampleRes(Sample sample) {
        return SampleResponse.builder()
                .sampleId(sample.getSampleId())
                .bloodGroup(sample.getBloodGroup())
                .quantity(sample.getQuantity())


                .build();
    }

    public   Sample mapToSampleReq(SampleRequest SampleRequest, Sample sample) {
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

}
