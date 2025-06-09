package com.example.orderservice.controller;

import com.ecommerce.proto.OrderProto;
import com.example.orderservice.service.OrderBusinessService;
import com.google.protobuf.util.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    @Autowired
    private OrderBusinessService orderBusinessService;

    /**
     * REST endpoint - trả về JSON
     * Route: GET /api/orders/large-data/{userId}
     */
    @GetMapping("/large-data/{userId}")
    public ResponseEntity<String> getLargeOrderData(@PathVariable("userId") String userId) {
        try {
            // Step back: Sử dụng chung business service
            OrderProto.Order order = orderBusinessService.generateLargeOrderData(userId);

            // Convert protobuf to JSON cho REST response
            String jsonResponse = JsonFormat.printer()
                    .includingDefaultValueFields()
                    .print(order);

            return ResponseEntity.ok(jsonResponse);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}