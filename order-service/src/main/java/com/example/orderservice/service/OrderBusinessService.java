package com.example.orderservice.service;

import com.ecommerce.proto.OrderProto;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderBusinessService {

    /**
     * Tạo một order với dữ liệu lớn để test performance
     * Method này được share giữa REST và gRPC
     */
    public OrderProto.Order generateLargeOrderData(String userId) {
        // Step back: Tạo order với nhiều items để có payload lớn
        List<OrderProto.OrderItem> items = new ArrayList<>();

        // Tạo 1000 items để có payload đủ lớn
        for (int i = 1; i <= 1000; i++) {
            OrderProto.OrderItem item = OrderProto.OrderItem.newBuilder()
                    .setProductId("product_" + i)
                    .setQuantity(i % 10 + 1)
                    .setUnitPrice(19.99 + (i % 100))
                    .setTotalPrice((19.99 + (i % 100)) * (i % 10 + 1))
                    .build();
            items.add(item);
        }

        // Tính tổng tiền
        double totalAmount = items.stream()
                .mapToDouble(OrderProto.OrderItem::getTotalPrice)
                .sum();

        // Tạo order với metadata đầy đủ
        return OrderProto.Order.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setUserId(userId)
                .addAllItems(items)
                .setTotalAmount(totalAmount)
                .setStatus(OrderProto.OrderStatus.PENDING)
                .setCreatedAt(Instant.now().toEpochMilli())
                .setUpdatedAt(Instant.now().toEpochMilli())
                .build();
    }
}