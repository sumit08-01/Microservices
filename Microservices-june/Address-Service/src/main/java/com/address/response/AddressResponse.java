package com.address.response;

import com.address.entity.Address;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class AddressResponse {

    private long addressId;

    private String street;
    private String city;

    public AddressResponse(Address address) {
        this.addressId = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
    }


}
