package com.address.service;

import com.address.entity.Address;
import com.address.repository.AddressRepository;
import com.address.request.AddressRequest;
import com.address.response.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressRepository addressRepository;

    public AddressResponse createAddress(AddressRequest addressRequest) {

        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        addressRepository.save(address);
        return new AddressResponse(address);
    }

    public AddressResponse getById(long id) {
        logger.info("Get By Id : "+ id);
       Address address = addressRepository.findById(id).get();
       return new AddressResponse(address);
    }
}
