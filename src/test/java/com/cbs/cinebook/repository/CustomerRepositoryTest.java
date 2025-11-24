
package com.cbs.cinebook.repository;

import com.cbs.cinebook.entity.CustomerEntity;
import com.cbs.cinebook.repositoty.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
class CustomerRepositoryTest {

    private final CustomerRepository customerRepository;

//    @Test
//    void testSaveAndFindByEmail() {
//        CustomerEntity entity = new CustomerEntity(
//                   "John", "Colombo",
//                "john@test.com", "0771234567", "25"
//        );
//
//        customerRepository.save(entity);
//
//        CustomerEntity found = customerRepository.findByEmail("john@test.com");
//
//        assertNotNull(found);
//        assertEquals("John", found.getName());
//    }
//
//    @Test
//    void testExistsByEmail() {
//        CustomerEntity entity = new CustomerEntity(
//                null, "John", "Colombo",
//                "john@test.com", "0771234567", "25"
//        );
//        customerRepository.save(entity);
//
//        assertTrue(customerRepository.existsByEmail("john@test.com"));
//        assertFalse(customerRepository.existsByEmail("no@test.com"));
//    }
}
