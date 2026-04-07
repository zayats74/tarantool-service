package com.example.tarantool_service.mapper;

import com.example.tarantool_service.dto.KeyValueDto;
import com.example.tarantool_service.model.KeyValue;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class KeyValueMapper {

    public KeyValue toEntity(KeyValueDto dto) {
        if (dto == null) {
            return null;
        }

        return KeyValue.builder()
                .key(dto.key())
                .value(dto.value() != null ? Arrays.toString(dto.value().toByteArray()).getBytes() : null)
                .build();
    }

    public KeyValueDto toDto(KeyValue entity) {
        if (entity == null) {
            return null;
        }

        return new KeyValueDto(
                entity.getKey(),
                entity.getValue() != null ? ByteString.copyFrom(entity.getValue()) : ByteString.EMPTY
        );
    }
}
