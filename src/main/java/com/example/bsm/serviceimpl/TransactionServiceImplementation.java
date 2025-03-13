package com.example.bsm.serviceimpl;

import com.example.bsm.entity.*;
import com.example.bsm.enums.TransactionType;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.exception.InsuficientUnitException;
import com.example.bsm.request.TransactionRequest;
import com.example.bsm.response.TransactionResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.TransactionService;

import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.TransactionRepository;
import com.example.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImplementation implements TransactionService {
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final AuthUtil authUtil;



    @Override
    public TransactionResponse registerUser(TransactionRequest transactionRequest, int hospitalId, int userId) throws Exception {
        Optional<Hospital> optional=hospitalRepository.findById(hospitalId);
        if (optional.isEmpty()){
            throw new HospitalNotFoundByIdException("Failed To find Hospital");
        }
        Optional<User> optional1=userRepository.findById(userId);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("Failed to find user");
        }
        Hospital hospital=optional.get();
        User user=optional1.get();
        Admin admin=authUtil.getCurrentAdmin();
        BloodBank bloodBank=admin.getBloodBank();
        Transaction transaction=this.mapToTransactionRequest(transactionRequest);
        List<Sample> samples=bloodBank.getSamples();
        Sample sample=null;
        for (Sample sample1:samples){
            if(sample1.getBloodGroup()==transaction.getBloodGroup()){
                sample=sample1;
            }
        }
        if (sample==null){
            throw new InsuficientUnitException("Not Available");
        }

        int i=sample.getAvailableUnits(),j= sample.getAvailableUnits();
        if(transaction.getTransactionType()== TransactionType.NORMAL){
            if(transaction.getNoOfUnits()>i){
                throw new InsuficientUnitException("Not Available");
            }else {
                sample.setAvailableUnits(i-transaction.getNoOfUnits());

            }
        }else {
            if(transaction.getNoOfUnits()>i){
                if (transaction.getNoOfUnits()>i+sample.getEmergencyUnit()){
                    throw new InsuficientUnitException("Not Found");
                }
                else {
                    sample.setAvailableUnits(0);
                    sample.setEmergencyUnit(sample.getEmergencyUnit()-transaction.getNoOfUnits()+j);

                }
            }else {
                sample.setAvailableUnits(i-transaction.getNoOfUnits());

            }
            sample.setQuantity(sample.getEmergencyUnit()+sample.getAvailableUnits());

            transaction.setUser(user);
            transaction.setHospital(hospital);
            transaction.setBloodBank(bloodBank);
            transactionRepository.save(transaction);


        }
        return TransactionResponse.builder()
                .transactionId(transaction.getTransactionId())
                .noOfUnits(transaction.getNoOfUnits())
                .build();
    }

    public   Transaction mapToTransactionRequest(TransactionRequest transactionRequest) {
        return Transaction.builder().noOfUnits(transactionRequest.getNoOfUnits())
                .transactionType(transactionRequest.getTransactionType())
                .bloodGroup(transactionRequest.getBloodGroup())
                .build();
    }
}
