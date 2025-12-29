package com.cbs.cinebook.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProjectSecurityConfigProdTest {

    @Autowired
    private ProjectSecurityConfigProd projectSecurityConfigProd;

    @Autowired
    private HttpSecurity httpSecurity;

    @Test
    void testSecurityFilterChainLoads() throws Exception {
        SecurityFilterChain chain = projectSecurityConfigProd.defaultSecurityFilterChain(httpSecurity);
        assertThat(chain).isNotNull();
    }
}
