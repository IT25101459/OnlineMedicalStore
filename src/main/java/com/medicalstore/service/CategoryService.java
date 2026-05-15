package com.medicalstore.service;

import com.medicalstore.model.Category;
import com.medicalstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final String FILE = "categories.txt";

    @Autowired
    private FileUtil fileUtil;

    public boolean add(Category category) {
        category.setCategoryId(fileUtil.generateId("C"));
        return fileUtil.append(FILE, category.toFileString());
    }

    public List<Category> getAll() {
        return fileUtil.readAll(FILE).stream()
                .map(Category::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Category findById(String id) {
        return getAll().stream()
                .filter(c -> c.getCategoryId().equals(id))
                .findFirst().orElse(null);
    }

    public boolean update(Category updated) {
        List<String> lines = getAll().stream()
                .map(c -> c.getCategoryId().equals(updated.getCategoryId()) ? updated.toFileString() : c.toFileString())
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }

    public boolean delete(String id) {
        List<String> lines = getAll().stream()
                .filter(c -> !c.getCategoryId().equals(id))
                .map(Category::toFileString)
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }
}
