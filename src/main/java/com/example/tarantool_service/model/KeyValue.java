package com.example.tarantool_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.protobuf.ByteString;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.tarantool.core.mapping.Tuple;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@KeySpace("KV")
@Tuple("KV")
@Builder
public class KeyValue {
    @Id
    @JsonProperty("key")
    @NonNull
    private String key;

    @JsonProperty("value")
    private byte[] value;
}
