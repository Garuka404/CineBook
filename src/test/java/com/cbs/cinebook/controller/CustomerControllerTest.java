package com.cbs.cinebook.controller;

import com.cbs.cinebook.dto.Customer;
import com.cbs.cinebook.dto.response.CustomerResponseDTO;
import com.cbs.cinebook.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CustomerService customerService;
//
//    @Test
//    void testGetAllCustomers() throws Exception {
//        CustomerResponseDTO response = new CustomerResponseDTO("OK", null);
//        Mockito.when(customerService.getAllCustomer())
//                .thenReturn(ResponseEntity.ok(response));
//
//        mockMvc.perform(get("/customer/all"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("OK"));
//    }
//
//    @Test
//    void testAddCustomer() throws Exception {
//        CustomerResponseDTO response = new CustomerResponseDTO("Saved", null);
//
//        Mockito.when(customerService.setCustomer(any(Customer.class)))
//                .thenReturn(ResponseEntity.ok(response));
//
//        String json = """
//                {
//                  "name": "John",
//                  "email": "john@test.com",
//                  "address": "Colombo",
//                  "contactNo": "0771234567",
//                  "age": "25"
//                }
//                """;
//
//        mockMvc.perform(post("/customer/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("Saved"));
//    }
//
//    @Test
//    void testUpdateCustomer() throws Exception {
//        CustomerResponseDTO response = new CustomerResponseDTO("Updated", null);
//
//        Mockito.when(customerService.updateCustomer(any(Customer.class)))
//                .thenReturn(ResponseEntity.ok(response));
//
//        String json = """
//                {
//                  "name": "John",
//                  "email": "john@test.com",
//                  "address": "Colombo",
//                  "contactNo": "0771234567",
//                  "age": "25"
//                }
//                """;
//
//        mockMvc.perform(put("/customer/update")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("Updated"));
//    }
//
//    @Test
//    void testGetCustomerByEmail() throws Exception {
//        CustomerResponseDTO response = new CustomerResponseDTO("FOUND", null);
//
//        Mockito.when(customerService.getCustomerByEmail(eq("john@test.com")))
//                .thenReturn(ResponseEntity.ok(response));
//
//        mockMvc.perform(get("/customer/get-by-email/john@test.com"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("FOUND"));
//    }
//
//    @Test
//    void testDeleteByEmail() throws Exception {
//        CustomerResponseDTO response = new CustomerResponseDTO("Deleted", null);
//
//        Mockito.when(customerService.deleteCustomer(eq("john@test.com")))
//                .thenReturn(ResponseEntity.ok(response));
//
//        mockMvc.perform(delete("/customer/delete-by-email/john@test.com"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message").value("Deleted"));
//    }
}
