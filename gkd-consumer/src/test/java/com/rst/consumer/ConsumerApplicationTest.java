package com.rst.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("local")
class ConsumerApplicationTest {
    @Autowired
    private ConsumerApplication application;

    @Test
    void contextLoads(){
        assertNotNull(application);
    }
}
