package com.example.bsm.mappper;

import com.example.bsm.request.AddressRequest;
import com.example.bsm.response.AddressResponse;
import com.example.bsm.entity.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressMapper {
    public AddressResponse mapToAddressRes(Address address) {
        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .addressLine(address.getAddressLine())
                .landMark(address.getLandMark())
                .area(address.getArea())
                .state(address.getState())
                .country(address.getCountry())
                .pinCode(address.getPinCode())
                .build();
    }

    public   Address mapToAddressReq(AddressRequest addressRequest, Address address) {
        address.setAddressLine(addressRequest.getAddressLine());
        address.setLandMark(addressRequest.getLandMark());
        address.setArea(addressRequest.getArea());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());
        address.setPinCode(addressRequest.getPinCode());
        return address;
    }
}
