package com.example.bsm.request;

import com.example.bsm.enums.TransactionType;
import com.example.bsm.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private int noOfUnits;
    private TransactionType transactionType;
    private BloodGroup bloodGroup;
}
