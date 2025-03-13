package com.example.bsm.service;

import com.example.bsm.response.AdminResponse;
import com.example.bsm.response.UserResponse;
import com.example.bsm.request.UserRequest;
import jakarta.validation.Valid;

import java.util.List;


public interface UserService {
    public UserResponse registerUser(UserRequest user);

    public List<UserResponse> findAllUsers();

    public UserResponse findUserById() throws Exception;

    public UserResponse deleteUserById() throws Exception;

    public UserResponse updateUserById(UserRequest userRequest) throws Exception;

    public UserResponse promoteUser(UserRequest userRequest);

    public UserResponse promoteUserById(int userId);

    AdminResponse registerAdmin(@Valid UserRequest userRequest);

    UserResponse verifyUserById(int userId, boolean isVerified);
}



