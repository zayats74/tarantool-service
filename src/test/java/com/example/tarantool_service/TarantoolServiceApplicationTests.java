package com.example.tarantool_service;

import com.example.tarantool_service.repository.KVRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TarantoolServiceApplicationTests {

    @MockBean
    private KVRepository KVRepository;

    @Test
    void contextLoads() {
    }
}
