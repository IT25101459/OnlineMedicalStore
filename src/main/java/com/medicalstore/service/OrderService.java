package com.medicalstore.service;

import com.medicalstore.model.Order;
import com.medicalstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final String FILE = "orders.txt";

    @Autowired
    private FileUtil fileUtil;

    public boolean place(Order order) {
        order.setOrderId(fileUtil.generateId("O"));
        order.setStatus("pending");
        order.setOrderDate(LocalDate.now().toString());
        return fileUtil.append(FILE, order.toFileString());
    }

    public List<Order> getAll() {
        return fileUtil.readAll(FILE).stream()
                .map(Order::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Order> getByUser(String userId) {
        return getAll().stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public Order findById(String orderId) {
        return getAll().stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst().orElse(null);
    }

    public boolean updateStatus(String orderId, String status) {
        List<String> lines = getAll().stream()
                .map(o -> {
                    if (o.getOrderId().equals(orderId)) {
                        o.setStatus(status);
                        return o.toFileString();
                    }
                    return o.toFileString();
                })
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }

    public boolean delete(String orderId) {
        List<String> lines = getAll().stream()
                .filter(o -> !o.getOrderId().equals(orderId))
                .map(Order::toFileString)
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }
}
