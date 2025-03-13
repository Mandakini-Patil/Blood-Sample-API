package com.example.bsm.service;

import com.example.bsm.request.UserRequest;
import com.example.bsm.response.UserResponse;

public interface AdminService {
    public UserResponse registerAdmin(UserRequest user);

    public UserResponse deleteAdminById(int adminId);

    public UserResponse updateUserById(UserRequest userRequest,int userId);
}
