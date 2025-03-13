package com.example.bsm.response;

import com.example.bsm.enums.AdminType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private int adminId;
    private AdminType adminType;
    private UserResponse userResponse;

}
