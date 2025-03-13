package com.example.bsm.request;

import com.example.bsm.enums.BloodGroup;
import com.example.bsm.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotNull(message="username cannot be null")
    @NotBlank(message = "username cannot be blank")
    private String userName;
    private String email;
    private Gender gender;
    @NotNull(message="password cannot be null")
    @NotBlank(message = "password cannot be blank")
    private String password;
    private String phoneNumber;
    private BloodGroup bloodGroup;
    @Min(1)
    @Max(100)
    private int age;
    private String availableCity;
    private LocalDate lastDonated;




}
