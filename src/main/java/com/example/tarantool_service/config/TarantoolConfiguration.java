package com.example.tarantool_service.config;

import io.tarantool.client.factory.TarantoolCrudClientBuilder;
import io.tarantool.client.factory.TarantoolFactory;
import io.tarantool.driver.api.TarantoolClient;
import io.tarantool.driver.api.TarantoolClientConfig;
import io.tarantool.driver.api.TarantoolClientFactory;
import io.tarantool.driver.api.tuple.TarantoolTuple;
import io.tarantool.driver.auth.SimpleTarantoolCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.tarantool.config.AbstractTarantoolDataConfiguration;
import org.springframework.data.tarantool.core.TarantoolTemplate;
import org.springframework.data.tarantool.core.convert.MappingTarantoolConverter;
import org.springframework.data.tarantool.core.mapping.TarantoolMappingContext;
import org.springframework.data.tarantool.repository.config.EnableTarantoolRepositories;

@Configuration
@EnableTarantoolRepositories(basePackages = "com.example.tarantool_service.repository")
public class TarantoolConfiguration extends AbstractTarantoolDataConfiguration{

    @Value("${tarantool.username}")
    private String username;

    @Value("${tarantool.password}")
    private String password;

    @Value("${tarantool.space}")
    private String space;

    @Value("${tarantool.host}")
    private String host;

    @Value("${tarantool.port}")
    private int port;

    @Bean
    public TarantoolCrudClientBuilder tarantoolClient(){
        return TarantoolFactory.crud()
                .withHost(host)
                .withPort(port)
                .withUser(username)
                .withPassword(password);
    }
}
