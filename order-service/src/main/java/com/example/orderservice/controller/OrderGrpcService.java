package com.example.orderservice.controller;

import com.ecommerce.proto.OrderProto;
import com.ecommerce.proto.OrderServiceGrpc;
import com.example.orderservice.service.OrderBusinessService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class OrderGrpcService extends OrderServiceGrpc.OrderServiceImplBase {

    @Autowired
    private OrderBusinessService orderBusinessService;

    /**
     * gRPC endpoint - trả về protobuf binary
     * Method: GetLargeOrderData (custom method cho test)
     */
    @Override
    public void createOrder(OrderProto.CreateOrderRequest request, StreamObserver<OrderProto.OrderResponse> responseObserver) {
        try {
            // Step back: Sử dụng chung business service
            OrderProto.Order order = orderBusinessService.generateLargeOrderData(request.getUserId());

            // Tạo gRPC response
            OrderProto.OrderResponse response = OrderProto.OrderResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Large order data generated successfully")
                    .setOrder(order)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            OrderProto.OrderResponse errorResponse = OrderProto.OrderResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Error: " + e.getMessage())
                    .build();

            responseObserver.onNext(errorResponse);
            responseObserver.onCompleted();
        }
    }
}