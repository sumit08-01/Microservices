package com.student.openfeignclinet;


import com.student.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "${address.service.url}",value = "address-feign-client",
//    path = "/api/address") // simple without eureka server
////@FeignClient(value = "address-service",
////        path = "/api/address") // simple with eureka server
//public interface AddressFeignClient {
//    @GetMapping("/getById/{id}")
//    public AddressResponse getById(@PathVariable long id);
//}


// The below interface now not directly call address-service, instead of call api-gateway-service and then get back response.
@FeignClient(value = "api-gateway",
        path = "/address-service/api/address") // simple with eureka server
public interface AddressFeignClient {
    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable long id);
}
