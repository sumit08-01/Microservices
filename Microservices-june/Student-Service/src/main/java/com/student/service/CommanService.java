package com.student.service;

import com.student.openfeignclinet.AddressFeignClient;
import com.student.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommanService {

    int count = 0;
    @Autowired
    private AddressFeignClient addressFeignClient;

    //    Address Response with the help of FeignClient
    @CircuitBreaker(name = "addressService", fallbackMethod = "fallBackgetAddressById") // about this method we are using circuitBreaker
    public AddressResponse getAddressById(long addressId){
        System.out.println("addressId" + addressId);
        System.out.println("Count : " + count);
        count++;
        AddressResponse addressResponse = addressFeignClient.getById(addressId);
        return addressResponse;
    }

    //fallBackMethod for the above circuitBreaker
    // In the fall Back method the method signature is same as the circuitBreaker method, if any thing wrong in the address-service then this method is called
    public AddressResponse fallBackgetAddressById(long addressId, Throwable throwable){
        System.out.println("Exception : " + throwable);
        return new AddressResponse();
    }
}
