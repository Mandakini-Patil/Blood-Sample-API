package com.example.bsm.response;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import com.example.bsm.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userId;
    private String userName;
    private Gender gender;
    private BloodGroup bloodGroup;
    private LocalDate lastDonated;
    private int age;
    private String availableCity;
    private boolean verified;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;
    private Role role;





}
