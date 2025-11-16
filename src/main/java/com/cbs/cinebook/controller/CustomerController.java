package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Customer;
import com.cbs.cinebook.dto.response.CustomerResponseDTO;
import com.cbs.cinebook.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<CustomerResponseDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }
    @PostMapping("/add")
    public ResponseEntity<CustomerResponseDTO> setCustomer(@RequestBody Customer customer) {
        return customerService.setCustomer(customer);
    }
    @PutMapping("/update")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }
    @DeleteMapping("/delete-by-email/{email}")
    public ResponseEntity<CustomerResponseDTO> deleteByEmail(@PathVariable String email) {
        return customerService.deleteCustomer(email);
    }
}
