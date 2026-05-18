package com.medicalstore.controller;

import com.medicalstore.model.User;
import com.medicalstore.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ── REGISTER ──────────────────────────────────────────────
    @GetMapping("/register")
    public String registerPage() {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String phone,
                           @RequestParam String address,
                           Model model) {
        User user = new User(null, username, email, password, phone, address, "user");
        if (userService.register(user)) {
            return "redirect:/user/login?success";
        }
        model.addAttribute("error", "Username already taken.");
        return "user/register";
    }

    // ── LOGIN ─────────────────────────────────────────────────
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("loggedUser", user);
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid credentials.");
        return "user/login";
    }

    // ── LOGOUT ────────────────────────────────────────────────
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }

    // ── PROFILE ───────────────────────────────────────────────
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        model.addAttribute("user", user);
        return "user/profile";
    }

    // ── EDIT PROFILE ──────────────────────────────────────────
    @GetMapping("/edit")
    public String editPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam String userId,
                       @RequestParam String email,
                       @RequestParam String phone,
                       @RequestParam String address,
                       HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);
        userService.update(user);
        session.setAttribute("loggedUser", user);
        return "redirect:/user/profile?updated";
    }

    // ── DELETE ACCOUNT ────────────────────────────────────────
    @PostMapping("/delete")
    public String delete(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        userService.delete(user.getUserId());
        session.invalidate();
        return "redirect:/user/login?deleted";
    }
}
