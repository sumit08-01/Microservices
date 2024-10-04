package com.address.controller;

import com.address.request.AddressRequest;
import com.address.response.AddressResponse;
import com.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@RefreshScope
public class AddressController {

    @Autowired
    private AddressService addressService;

//    @Value("${address.test}")
//    private String test;

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody AddressRequest addressRequest){
        return addressService.createAddress(addressRequest);
    }

    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable long id){
        System.out.println("Address Service called");
        return addressService.getById(id);
    }

//    @GetMapping("/testfordev")
//    public String testPropertiesFromDevProfile(){
//        return test;
//    }


}
