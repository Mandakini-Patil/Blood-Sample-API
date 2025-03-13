package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Survey;
import com.example.bsm.entity.User;
import com.example.bsm.request.SurveyRequest;
import com.example.bsm.response.SurveyResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.SurveyService;
import com.example.bsm.exception.SurveyNotFoundByIdException;
import com.example.bsm.repository.SurveyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SurveyServiceImplementation implements SurveyService {



    private final SurveyRepository repository;
    private final AuthUtil authUtil;

    private SurveyResponse mapToSurveyRes(Survey survey) {
        return SurveyResponse.builder()
                .surveyId(survey.getSurveyId())
                .consumedTobacco(survey.isConsumedTobacco())
                .preMedicalCondition(survey.isPreMedicalCondition())
                .consumedAlcohol(survey.isConsumedAlcohol())
                .addictive(survey.isAddictive())
                .medicines(survey.isMedicines())
                .build();
    }

    private  Survey mapToSurveyReq(SurveyRequest surveyRequest, Survey survey) {
        survey.setPreMedicalCondition(surveyRequest.isPreMedicalCondition());
        survey.setConsumedAlcohol(surveyRequest.isConsumedAlcohol());
        survey.setConsumedTobacco(surveyRequest.isConsumedTobacco());
        survey.setAddictive(surveyRequest.isAddictive());
        survey.setMedicines(surveyRequest.isMedicines());
        return survey;
    }


    @Override
    public SurveyResponse registerSurvey(SurveyRequest SurveyRequest) throws Exception {
        User user=authUtil.getCurrentUser();
        Survey survey= this.mapToSurveyReq(SurveyRequest,new Survey());
        survey.setUser(user);
        survey=repository.save(survey);
        return this.mapToSurveyRes(survey);
    }

    @Override
    public SurveyResponse findSurveyById(int SurveyId) {
        Optional<Survey> optional=repository.findById(SurveyId);
        if (optional.isEmpty()){
            throw new SurveyNotFoundByIdException("Failed to find Survey");
        }
        Survey Survey=optional.get();
        return mapToSurveyRes(Survey);
    }


    @Override
    public SurveyResponse updateSurveyById(SurveyRequest SurveyRequest,int SurveyId) {
        Optional<Survey> optional=repository.findById(SurveyId);

        if (optional.isEmpty()){
            throw new SurveyNotFoundByIdException("Failed to update");
        }

        Survey survey=this.mapToSurveyReq(SurveyRequest,optional.get());
        survey=repository.save(survey);
        return this.mapToSurveyRes(survey);
    }


















    @Override
    public List<SurveyResponse> findAllSurveys()
    {
        List<Survey> l=repository.findAll();
        if (l.isEmpty()){
            throw new SurveyNotFoundByIdException("failed to find blood bank");
        }
        List<SurveyResponse> res=new ArrayList<>();
        for(Survey Survey:l)
        {
            res.add(this.mapToSurveyRes(Survey));
        }
        return res;
    }



    @Override
    public SurveyResponse deleteSurveyById(int SurveyId) {
        Optional<Survey> optional=repository.findById(SurveyId);
        if(optional.isPresent()) {
            Survey a=optional.get();
            repository.delete(a);
            return this.mapToSurveyRes(a);
        }else {
            throw new RuntimeException("Failed to delete Actor");
        }
    }


}
