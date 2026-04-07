package com.example.tarantool_service.service.Impl;

import com.example.tarantool_service.dto.KeyValueDto;
import com.example.tarantool_service.mapper.KeyValueMapper;
import com.example.tarantool_service.model.KeyValue;
import com.example.tarantool_service.repository.KVRepository;
import com.example.tarantool_service.service.TarantoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarantoolServiceImpl implements TarantoolService {

    private final KVRepository KVRepository;

    private final KeyValueMapper keyValueMapper;

    @Override
    public void put(KeyValueDto keyValueDto) {
        KeyValue keyValue = keyValueMapper.toEntity(keyValueDto);
        KVRepository.save(keyValue);
    }

    @Override
    public Optional<byte[]> get(String key) {
        return KVRepository.findById(key).map(KeyValue::getValue);
    }

    @Override
    public void delete(String key) {
        KVRepository.deleteById(key);
    }

    @Override
    public List<KeyValueDto> range(String keySince, String keyTo) {
        return KVRepository.findByKeyBetween(keySince, keyTo)
                .stream()
                .map(keyValueMapper::toDto)
                .toList();
    }

    @Override
    public Long count() {
        return KVRepository.count();
    }
}
