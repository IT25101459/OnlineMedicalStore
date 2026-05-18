package com.medicalstore.controller;

import com.medicalstore.model.Payment;
import com.medicalstore.model.User;
import com.medicalstore.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired private PaymentService paymentService;

    @GetMapping
    public String myPayments(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        model.addAttribute("payments", paymentService.getByUser(user.getUserId()));
        return "payment/list";
    }

    @GetMapping("/add")
    public String addPage(@RequestParam String orderId,
                          @RequestParam double amount,
                          Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("amount", amount);
        return "payment/add";
    }

    @PostMapping("/add")
    public String add(@RequestParam String orderId,
                      @RequestParam double amount,
                      @RequestParam String method,
                      HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/user/login";
        Payment p = new Payment(null, orderId, user.getUserId(), amount, method, "completed", null);
        paymentService.add(p);
        return "redirect:/payment?success";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        paymentService.delete(id);
        return "redirect:/payment";
    }
}
