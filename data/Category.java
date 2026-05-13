package com.medicalstore.model;

public class Category {
    private String categoryId;
    private String name;
    private String description;

    public Category() {}

    public Category(String categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public String toFileString() {
        return categoryId + "," + name + "," + description;
    }

    public static Category fromFileString(String line) {
        String[] parts = line.split(",", 3);
        if (parts.length < 3) return null;
        return new Category(parts[0], parts[1], parts[2]);
    }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
