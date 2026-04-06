package com.example.tarantool_service.repository;

import com.example.tarantool_service.model.KeyValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.tarantool.repository.TarantoolRepository;

import java.util.List;
import java.util.Optional;

public interface KVRepository extends CrudRepository<KeyValue, String> {

//    void save(KeyValue keyValue);
//    void deleteById(String key);
//    Optional<KeyValue> findById(String key);
    List<KeyValue> findByKeyBetween(String keySince, String keyTo);
//    Long count();
}
