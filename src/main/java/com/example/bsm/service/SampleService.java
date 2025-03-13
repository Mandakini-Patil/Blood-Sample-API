package com.example.bsm.service;

import com.example.bsm.response.SampleResponse;
import com.example.bsm.request.SampleRequest;

import java.util.List;

public interface SampleService {
    public SampleResponse registerSample(SampleRequest Sample, int bloodBankId);

    public List<SampleResponse> findAllSamples();

    public SampleResponse findSampleById(int SampleId);

    public SampleResponse deleteSampleById(int SampleId);

    public SampleResponse updateSampleById(SampleRequest SampleRequest,int SampleId);


}
