package com.medicalstore.model;

public class Order {
    private String orderId;
    private String userId;
    private String medicineId;
    private String medicineName;
    private int quantity;
    private double totalAmount;
    private String status; // "pending", "confirmed", "delivered", "cancelled"
    private String orderDate;

    public Order() {}

    public Order(String orderId, String userId, String medicineId, String medicineName,
                 int quantity, double totalAmount, String status, String orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
    }

    public String toFileString() {
        return orderId + "," + userId + "," + medicineId + "," + medicineName + "," +
               quantity + "," + totalAmount + "," + status + "," + orderDate;
    }

    public static Order fromFileString(String line) {
        String[] parts = line.split(",", 8);
        if (parts.length < 8) return null;
        return new Order(parts[0], parts[1], parts[2], parts[3],
                Integer.parseInt(parts[4]), Double.parseDouble(parts[5]),
                parts[6], parts[7]);
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMedicineId() { return medicineId; }
    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }

    public String getMedicineName() { return medicineName; }
    public void setMedicineName(String medicineName) { this.medicineName = medicineName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
}
