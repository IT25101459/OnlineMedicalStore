package com.medicalstore.model;

// INHERITANCE: User extends Person (inherits id, name, email, phone, address)
public class User extends Person {
    private String username;
    private String password;
    private String role; // "user" or "admin"

    public User() {}

    public User(String userId, String username, String email, String password,
                String phone, String address, String role) {
        super(userId, username, email, phone, address);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // POLYMORPHISM: Overrides abstract method from Person
    @Override
    public String getRole() { return role; }

    // POLYMORPHISM: Overrides getDisplayInfo() from Person
    @Override
    public String getDisplayInfo() {
        return username + " [" + role.toUpperCase() + "] - " + email;
    }

    public String getUserId() { return id; }
    public void setUserId(String userId) { this.id = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }

    public String toFileString() {
        return id + "," + username + "," + email + "," + password + "," +
               phone + "," + address + "," + role;
    }

    public static User fromFileString(String line) {
        String[] parts = line.split(",", 7);
        if (parts.length < 7) return null;
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
    }
}
