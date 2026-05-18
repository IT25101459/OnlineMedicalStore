package com.medicalstore.service;

import com.medicalstore.model.Supplier;
import com.medicalstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private static final String FILE = "suppliers.txt";

    @Autowired
    private FileUtil fileUtil;

    public boolean add(Supplier supplier) {
        supplier.setSupplierId(fileUtil.generateId("S"));
        return fileUtil.append(FILE, supplier.toFileString());
    }

    public List<Supplier> getAll() {
        return fileUtil.readAll(FILE).stream()
                .map(Supplier::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Supplier findById(String id) {
        return getAll().stream()
                .filter(s -> s.getSupplierId().equals(id))
                .findFirst().orElse(null);
    }

    public boolean update(Supplier updated) {
        List<String> lines = getAll().stream()
                .map(s -> s.getSupplierId().equals(updated.getSupplierId()) ? updated.toFileString() : s.toFileString())
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }

    public boolean delete(String id) {
        List<String> lines = getAll().stream()
                .filter(s -> !s.getSupplierId().equals(id))
                .map(Supplier::toFileString)
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }
}
