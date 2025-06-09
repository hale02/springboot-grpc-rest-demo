package com.example.orderservice.config;

import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GrpcServerAutoConfiguration.class)
public class GrpcConfig {
    // gRPC sẽ chạy trên port 9090 (default)
    // REST sẽ chạy trên port 8080 (default)
}