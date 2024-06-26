package com.dekankilic.customer.service;

import com.dekankilic.customer.dto.CustomerRequest;
import com.dekankilic.customer.dto.CustomerResponse;
import com.dekankilic.customer.exception.ResourceNotFoundException;
import com.dekankilic.customer.mapper.CustomerMapper;
import com.dekankilic.customer.model.Customer;
import com.dekankilic.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id()).orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", request.id()));
        mergeCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(mapper::toCustomerResponse).collect(Collectors.toList());
    }

    public Boolean existsById(String id) {
        return customerRepository.findById(id).isPresent();
    }

    public CustomerResponse findById(String id) {
        return customerRepository.findById(id).map(mapper::toCustomerResponse).orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", id));
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
