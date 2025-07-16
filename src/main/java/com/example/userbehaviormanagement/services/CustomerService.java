package com.example.userbehaviormanagement.services;

import com.example.userbehaviormanagement.entities.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(String id);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(String id, CustomerDTO customerDTO);
    void deleteCustomer(String id);
}
