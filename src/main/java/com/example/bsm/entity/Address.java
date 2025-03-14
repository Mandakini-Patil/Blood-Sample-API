package com.example.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String addressLine;
    private String landMark;
    private String area;
    private String city;
    private String state;
    private String country;
    private int pinCode;

    @OneToOne
    private User user;

    @OneToOne
    private Hospital hospital;

    @OneToOne
    private BloodBank bloodBank;

}
