package com.example.bsm.controller;

import com.example.bsm.request.SurveyRequest;
import com.example.bsm.response.SurveyResponse;
import com.example.bsm.service.SurveyService;
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
public class SurveyController {
    private final SurveyService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/Surveys")
    public ResponseEntity<ResponseStructure<SurveyResponse>> registerSurvey(@RequestBody SurveyRequest SurveyRequest) throws Exception {
        SurveyResponse a=service.registerSurvey(SurveyRequest);
        return responseBuilder.success(HttpStatus.CREATED,"Survey Created",a);
    }

    @PutMapping("/Surveys")
    public ResponseEntity<ResponseStructure<SurveyResponse>> updateSurveyById(@RequestBody  SurveyRequest SurveyRequest,
                                                                              int SurveyId) {
        SurveyResponse u=service.updateSurveyById(SurveyRequest,SurveyId);
        return responseBuilder.success(HttpStatus.FOUND,"Survey Updated",u);
    }

    @GetMapping("/Survey")
    public ResponseEntity<ResponseStructure<SurveyResponse>> findSurveyById(@RequestParam int SurveyId){
        SurveyResponse f=service.findSurveyById(SurveyId);
        return responseBuilder.success(HttpStatus.FOUND,"Survey Found",f);
    }



}
