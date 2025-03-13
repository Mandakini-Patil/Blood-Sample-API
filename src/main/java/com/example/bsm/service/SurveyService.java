package com.example.bsm.service;

import com.example.bsm.response.SurveyResponse;
import com.example.bsm.request.SurveyRequest;

import java.util.List;

public interface SurveyService {
    public SurveyResponse registerSurvey(SurveyRequest Survey) throws Exception;

    public List<SurveyResponse> findAllSurveys();

    public SurveyResponse findSurveyById(int SurveyId);

    public SurveyResponse deleteSurveyById(int SurveyId);

    public SurveyResponse updateSurveyById(SurveyRequest SurveyRequest,int SurveyId);


}
