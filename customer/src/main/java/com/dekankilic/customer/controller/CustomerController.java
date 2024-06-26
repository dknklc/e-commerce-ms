package com.dekankilic.customer.controller;

import com.dekankilic.customer.dto.CustomerRequest;
import com.dekankilic.customer.dto.CustomerResponse;
import com.dekankilic.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        customerService.updateCustomer(request);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.findAllCustomers());
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.existsById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        customerService.deleteCustomer(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

}
