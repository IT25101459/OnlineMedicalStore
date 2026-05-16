package com.medicalstore.model;

// ABSTRACTION: Abstract base class for medicines
public abstract class MedicineBase {
    protected String medicineId;
    protected String name;
    protected String categoryId;
    protected String supplierId;
    protected double price;
    protected int stock;
    protected String description;
    protected String expiryDate;

    public MedicineBase() {}

    public MedicineBase(String medicineId, String name, String categoryId, String supplierId,
                        double price, int stock, String description, String expiryDate) {
        this.medicineId = medicineId;
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    // ABSTRACTION: Subclasses must define their medicine type
    public abstract String getMedicineType();

    // POLYMORPHISM: Can be overridden for different price display
    public String getFormattedPrice() {
        return "Rs. " + String.format("%.2f", price);
    }

    // POLYMORPHISM: Can be overridden for different availability logic
    public boolean isAvailable() {
        return stock > 0;
    }

    public String getMedicineId() { return medicineId; }
    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
}
