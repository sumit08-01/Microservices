package com.student.service;

import com.student.entity.Student;
import com.student.openfeignclinet.AddressFeignClient;
import com.student.repository.StudentRepository;
import com.student.request.StudentRequest;
import com.student.response.AddressResponse;
import com.student.response.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class StudentService {


    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private WebClient webClient; // with the help of this we calling restcalls, we are using this because ,resttemplate is going to depricated.

    @Autowired
    private AddressFeignClient addressFeignClient; // We are using the OpenFeignClinet to call the external rest-service

    @Autowired
    private CommanService commanService;
    public StudentResponse createStudent(StudentRequest studentRequest) {

        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAddressId(studentRequest.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId())); // this is with the help of webClient
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId())); // this is with the help of the Open-feignClient
        studentResponse.setAddressResponse(commanService.getAddressById(student.getAddressId())); // this is with the help of the Comman servie in this class circuit breaker and fall back method is define
        return studentResponse;
    }

    public StudentResponse getById (long id) {
        logger.info("Inside student getById : " + id);
//        return new StudentResponse(studentRepository.findById(id).get()); // we are not returning directly like this
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getId())); // this is with the help of webClient
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));// this is with the help of the Open-feignClient
        studentResponse.setAddressResponse(commanService.getAddressById(student.getAddressId()));
        return studentResponse;
    }

    //    Address Response with the help of FeignClient
//    @CircuitBreaker(name = "addressService", fallbackMethod = "fallBackgetAddressById") // about this method we are using circuitBreaker
//    public AddressResponse getAddressById(long addressId){
//        System.out.println("addressId" + addressId);
//        AddressResponse addressResponse = addressFeignClient.getById(addressId);
//        return addressResponse;
//    }
//
//    //fallBackMethod for the above circuitBreaker
//    // In the fall Back method the method signature is same as the circuitBreaker method, if any thing wrong in the address-service then this method is called
//    public AddressResponse fallBackgetAddressById(long addressId, Throwable throwable){
//    return new AddressResponse();
//    }

//    Address Response with the help of webClient
//    public AddressResponse getAddressById(long addressId){
//        System.out.println("addressId" + addressId);
//        Mono<AddressResponse> addressResponseMono = webClient.
//                        get(). // it is a get request
//                        uri("/getById/" + addressId). // to this @GettMapping("/getById") method from the address-service
//                        retrieve(). // and retrieve the data from that
//                        bodyToMono(AddressResponse.class); // After retrieved data we have to convet this our own response class
//        System.out.println("Address Response : " + webClient.get().toString());
//        return addressResponseMono.block(); // the return response to student-service
//    }
}
