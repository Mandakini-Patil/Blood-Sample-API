package com.example.bsm.service;

import com.example.bsm.response.TransactionResponse;
import com.example.bsm.request.TransactionRequest;

public interface TransactionService {


    public TransactionResponse registerUser(TransactionRequest transactionRequest, int hospitalId, int userId) throws Exception;
}
