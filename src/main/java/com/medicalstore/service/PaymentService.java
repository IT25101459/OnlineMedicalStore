package com.medicalstore.service;

import com.medicalstore.model.Payment;
import com.medicalstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private static final String FILE = "payments.txt";

    @Autowired
    private FileUtil fileUtil;

    public boolean add(Payment payment) {
        payment.setPaymentId(fileUtil.generateId("P"));
        payment.setPaymentDate(LocalDate.now().toString());
        return fileUtil.append(FILE, payment.toFileString());
    }

    public List<Payment> getAll() {
        return fileUtil.readAll(FILE).stream()
                .map(Payment::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Payment findById(String id) {
        return getAll().stream()
                .filter(p -> p.getPaymentId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Payment> getByUser(String userId) {
        return getAll().stream()
                .filter(p -> p.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public boolean updateStatus(String paymentId, String status) {
        List<String> lines = getAll().stream()
                .map(p -> {
                    if (p.getPaymentId().equals(paymentId)) {
                        p.setStatus(status);
                        return p.toFileString();
                    }
                    return p.toFileString();
                })
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }

    public boolean delete(String id) {
        List<String> lines = getAll().stream()
                .filter(p -> !p.getPaymentId().equals(id))
                .map(Payment::toFileString)
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }
}
