package com.cbs.cinebook.service.impl;

import com.cbs.cinebook.entity.CustomerEntity;
import com.cbs.cinebook.repositoty.CustomerRepository;
import com.cbs.cinebook.service.UserSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSyncServiceImpl implements UserSyncService {

    private final CustomerRepository customerRepository;


    @Override
    public void syncUser(Jwt jwt) {
        String keycloakId = jwt.getSubject(); // sub
        String email = jwt.getClaimAsString("email");
        String username = jwt.getClaimAsString("preferred_username");

        String age = jwt.getClaim("age");
        String address = jwt.getClaimAsString("address");
        String number = jwt.getClaimAsString("number");

        customerRepository.findByKeyClockId(keycloakId)
                .ifPresentOrElse(
                        existing -> System.out.println("✅ User already exists"),
                        () -> {
                            CustomerEntity customer = new CustomerEntity();
                            customer.setKeyClockId(keycloakId);
                            customer.setEmail(email);
                            customer.setName(username);
                            customer.setAge(age);
                            customer.setAddress(address);
                            customer.setNumber(number);
                            customerRepository.save(customer);
                        }
                );
    }
}