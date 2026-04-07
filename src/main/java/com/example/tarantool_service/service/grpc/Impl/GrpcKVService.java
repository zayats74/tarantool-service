package com.example.tarantool_service.service.grpc.Impl;

import com.example.tarantool_service.dto.KeyValueDto;
import com.example.tarantool_service.model.KeyValue;
import com.example.tarantool_service.service.TarantoolService;
import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import kvservice.KVServiceGrpc;
import kvservice.Kv;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Arrays;
import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class GrpcKVService extends KVServiceGrpc.KVServiceImplBase {

    private final TarantoolService tarantoolService;

    @Override
    public void putKV(Kv.PutKVRequest request, StreamObserver<Empty> responseObserver) {
        KeyValueDto kv = KeyValueDto.builder()
                .key(request.getKey())
                .value(request.getValue())
                .build();

        tarantoolService.put(kv);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void getKV(Kv.GetKVRequest request, StreamObserver<Kv.GetKVResponse> responseObserver) {
        tarantoolService.get(request.getKey())
                .ifPresentOrElse(
                        value -> {
                            Kv.GetKVResponse response = Kv.GetKVResponse.newBuilder()
                                    .setValue(ByteString.copyFrom(value))
                                    .build();
                            responseObserver.onNext(response);
                            responseObserver.onCompleted();
                        },
                        () -> {
                            responseObserver.onNext(Kv.GetKVResponse.getDefaultInstance());
                            responseObserver.onCompleted();
                        }
                );
    }

    @Override
    public void deleteKV(Kv.DeleteKVRequest request, StreamObserver<Empty> responseObserver) {
        tarantoolService.delete(request.getKey());
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void rangeKV(Kv.RangeKVRequest request, StreamObserver<Kv.RangeKVResponse> responseObserver) {
        List<KeyValueDto> list = tarantoolService.range(request.getKeySince(), request.getKeyTo());
        for(KeyValueDto kv : list){
            responseObserver.onNext(Kv.RangeKVResponse
                    .newBuilder()
                            .setKey(kv.key())
                            .setValue(kv.value())
                    .build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void count(Empty request, StreamObserver<Kv.CountKVResponse> responseObserver) {
        Long count = tarantoolService.count();
        Kv.CountKVResponse response = Kv.CountKVResponse.newBuilder()
                .setCount(count)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
