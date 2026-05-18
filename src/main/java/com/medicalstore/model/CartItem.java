package com.medicalstore.model;

public class CartItem {
    private String cartItemId;
    private String userId;
    private String medicineId;
    private String medicineName;
    private int quantity;
    private double price;

    public CartItem() {}

    public CartItem(String cartItemId, String userId, String medicineId,
                    String medicineName, int quantity, double price) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotal() {
        return quantity * price;
    }

    public String toFileString() {
        return cartItemId + "," + userId + "," + medicineId + "," +
               medicineName + "," + quantity + "," + price;
    }

    public static CartItem fromFileString(String line) {
        String[] parts = line.split(",", 6);
        if (parts.length < 6) return null;
        return new CartItem(parts[0], parts[1], parts[2], parts[3],
                Integer.parseInt(parts[4]), Double.parseDouble(parts[5]));
    }

    public String getCartItemId() { return cartItemId; }
    public void setCartItemId(String cartItemId) { this.cartItemId = cartItemId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMedicineId() { return medicineId; }
    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }

    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
