package com.cbs.cinebook.filter;

import com.cbs.cinebook.dto.Customer;
import com.cbs.cinebook.entity.CustomerEntity;
import com.cbs.cinebook.repositoty.CustomerRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class KeycloakUserSyncFilter{
//    private final ModelMapper modelMapper;
//    private final CustomerRepository customerRepository;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////
////        // Make sure this is an authenticated Keycloak JWT
////        if (!(auth instanceof JwtAuthenticationToken jwtAuth)) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        Jwt jwt = jwtAuth.getToken();
////
////        // ---- SAFE CLAIM ACCESS ----
////        String keycloakId = jwt.getSubject(); // always exists
////
////        String email = (String) jwt.getClaims().get("email"); // MAYBE NULL
////        String username = (String) jwt.getClaims().get("preferred_username"); // default keycloak field
////
////        // Custom attributes - might not exist
////        String address = (String) jwt.getClaims().get("address");
////        String age = (String) jwt.getClaims().get("age");
////        String number = (String) jwt.getClaims().get("number");
////
////        // If email null → skip sync (invalid user)
////        if (email == null) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        // ---- SYNC ONLY IF USER DOES NOT EXIST ----
////        boolean exists = customerRepository.existsByKeyClockId(keycloakId);
////        if (!exists) {
////            Customer customer = new Customer();
////            customer.setKeyClockId(keycloakId);
////            customer.setEmail("email");
////            customer.setName("username");
////            customer.setAddress("address");
////            customer.setAge("age");
////            customer.setContactNo("number");
////
////            customerRepository.save(modelMapper.map(customer, CustomerEntity.class));
////        }
//
//        filterChain.doFilter(request, response);
//    }
}
