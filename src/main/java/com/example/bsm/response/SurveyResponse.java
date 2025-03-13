package com.example.bsm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResponse {
    private int surveyId;
    private boolean preMedicalCondition;
    private boolean consumedAlcohol;
    private boolean consumedTobacco;
    private boolean addictive;
    private boolean medicines;
}
