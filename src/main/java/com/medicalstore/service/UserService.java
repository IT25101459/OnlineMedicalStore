package com.medicalstore.service;

import com.medicalstore.model.User;
import com.medicalstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String FILE = "users.txt";

    @Autowired
    private FileUtil fileUtil;

    // CREATE
    public boolean register(User user) {
        // Check if username already exists
        if (findByUsername(user.getUsername()) != null) return false;
        user.setUserId(fileUtil.generateId("U"));
        return fileUtil.append(FILE, user.toFileString());
    }

    // READ - get all users
    public List<User> getAllUsers() {
        return fileUtil.readAll(FILE).stream()
                .map(User::fromFileString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // READ - find by username
    public User findByUsername(String username) {
        return getAllUsers().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst().orElse(null);
    }

    // READ - find by ID
    public User findById(String userId) {
        return getAllUsers().stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst().orElse(null);
    }

    // LOGIN check
    public User login(String username, String password) {
        User user = findByUsername(username);
        if (user != null && user.getPassword().equals(password)) return user;
        return null;
    }

    // UPDATE
    public boolean update(User updated) {
        List<String> lines = getAllUsers().stream()
                .map(u -> u.getUserId().equals(updated.getUserId()) ? updated.toFileString() : u.toFileString())
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }

    // DELETE
    public boolean delete(String userId) {
        List<String> lines = getAllUsers().stream()
                .filter(u -> !u.getUserId().equals(userId))
                .map(User::toFileString)
                .collect(Collectors.toList());
        return fileUtil.writeAll(FILE, lines);
    }
}
