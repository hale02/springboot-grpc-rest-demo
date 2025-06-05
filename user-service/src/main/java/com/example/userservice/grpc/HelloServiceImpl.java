package com.example.userservice.grpc;

import com.example.base.service.BankService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Autowired
    private BankService bankService;

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloBaseResponse> responseObserver) {
        String name = request.getName();

        String tyGia = bankService.layTiGiaHienTai(LocalDate.now(), "6.3");

        String message = "Xin chào " + name + " từ gRPC! Tỷ giá hôm nay là " + tyGia;
        if ("0".equalsIgnoreCase(tyGia)) {
            HelloBaseResponse helloBaseResponse = HelloBaseResponse.newBuilder()
                    .setCode("01")
                    .setMessage("Ty gia = 0")
                    .build();

            responseObserver.onNext(helloBaseResponse);
            responseObserver.onCompleted(); // đóng stream
        }

        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(message)
                .build();

        HelloBaseResponse helloBaseResponse = HelloBaseResponse.newBuilder()
                .setCode("00")
                .setMessage("Success")
                .setData(response)
                .build();

        responseObserver.onNext(helloBaseResponse);
        responseObserver.onCompleted(); // đóng stream
    }
}