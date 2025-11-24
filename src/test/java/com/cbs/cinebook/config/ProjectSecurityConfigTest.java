package com.cbs.cinebook.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProjectSecurityConfigTest {

    @Autowired
    private ProjectSecurityConfig projectSecurityConfig;

    @Autowired
    private HttpSecurity httpSecurity;

    @Test
    void testSecurityFilterChainLoads() throws Exception {
        SecurityFilterChain chain = projectSecurityConfig.defaultSecurityFilterChain(httpSecurity);
        assertThat(chain).isNotNull();
    }
}
