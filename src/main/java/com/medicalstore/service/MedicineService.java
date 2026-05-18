package com.medicalstore.service;

import com.medicalstore.model.Medicine;
import com.medicalstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicineService {

    private static final String FILE = "medicines.txt";

    @Autowired
    private FileUtil fileUtil;

    // CREATE
    public boolean add(Medicine medicine) {
        medicine.setMedicineId(fileUtil.generateId("M"));
        return fileUtil.append(FILE, medicine.toFileString());
    }

    // READ all
    public List<Medicine> getAll() {
        return fileUtil.readAll(FILE).stream()
                .map(Medicine::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // READ by ID
    public Medicine findById(String id) {
        return getAll().stream()
                .filter(m -> m.getMedicineId().equals(id))
                .findFirst().orElse(null);
    }

    // SEARCH by name (case-insensitive)
    public List<Medicine> search(String keyword) {
        return getAll().stream()
                .filter(m -> m.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // UPDATE
    public boolean update(Medicine updated) {
        List<String> lines = getAll().stream()
                .map(m -> m.getMedicineId().equals(updated.getMedicineId()) ? updated.toFileString() : m.toFileString())
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }

    // DELETE
    public boolean delete(String id) {
        List<String> lines = getAll().stream()
                .filter(m -> !m.getMedicineId().equals(id))
                .map(Medicine::toFileString)
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }
}
