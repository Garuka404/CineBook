package com.cbs.cinebook.service;

import com.cbs.cinebook.dto.Customer;
import com.cbs.cinebook.dto.response.CustomerResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<CustomerResponseDTO> getCustomerByEmail(String email);
    ResponseEntity<CustomerResponseDTO>  setCustomer(Customer customer);
    ResponseEntity<CustomerResponseDTO>  updateCustomer(Customer customer);
    ResponseEntity<CustomerResponseDTO>  deleteCustomer(String email);
    ResponseEntity<List<Customer>> getAllCustomer();


}
