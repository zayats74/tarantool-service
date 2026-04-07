package com.example.tarantool_service.service;

import com.example.tarantool_service.dto.KeyValueDto;
import com.example.tarantool_service.model.KeyValue;

import java.util.List;
import java.util.Optional;

public interface TarantoolService {
    void put(KeyValueDto keyValueDto);
    Optional<byte[]> get(String key);
    void delete(String key);
    List<KeyValueDto> range(String keySince, String keyTo);
    Long count();
}
