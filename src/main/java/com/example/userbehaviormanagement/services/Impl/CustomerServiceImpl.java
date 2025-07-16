package com.example.userbehaviormanagement.services.Impl;

import com.example.userbehaviormanagement.entities.Customer;
import com.example.userbehaviormanagement.entities.Role;
import com.example.userbehaviormanagement.entities.dto.CustomerDTO;
import com.example.userbehaviormanagement.entities.dto.RoleDTO;
import com.example.userbehaviormanagement.enums.UserStatus;
import com.example.userbehaviormanagement.repositories.CustomerRepository;
import com.example.userbehaviormanagement.services.CustomerService;
import com.example.userbehaviormanagement.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private static final String DEFAULT_AVATAR_PATH = "/data/images/c21f969b5f03d33d43e04f8f136e7682.png";
    private final RoleService roleService;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        UUID customerId = UUID.fromString(id);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        return customerOptional.map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Role role = roleService.getRoleByName("Customer");
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        if (!StringUtils.hasText(customerDTO.getAvatar())) {
            customerDTO.setAvatar(DEFAULT_AVATAR_PATH);
        }

        customer.setRole(role);
        customer.setStatus(UserStatus.ACTIVE);
        customer.setAvatar(customerDTO.getAvatar());
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO updateCustomer(String id, CustomerDTO customerDTO) {
        UUID customerId = UUID.fromString(id);
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        modelMapper.map(customerDTO, existingCustomer);
        existingCustomer.setRole(roleService.getRoleByName(customerDTO.getRoleName()));
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(String id) {
        UUID customerId = UUID.fromString(id);
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(existingCustomer);
    }
}
