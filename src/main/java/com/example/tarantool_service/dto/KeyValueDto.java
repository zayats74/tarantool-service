package com.example.tarantool_service.dto;

import com.google.protobuf.ByteString;
import lombok.Builder;
import org.msgpack.value.BinaryValue;

@Builder
public record KeyValueDto(String key, ByteString value) { }
