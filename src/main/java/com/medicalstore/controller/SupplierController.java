package com.medicalstore.controller;

import com.medicalstore.model.Supplier;
import com.medicalstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired private SupplierService supplierService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("suppliers", supplierService.getAll());
        return "supplier/list";
    }

    @GetMapping("/add")
    public String addPage() { return "supplier/add"; }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String email,
                      @RequestParam String phone, @RequestParam String address) {
        supplierService.add(new Supplier(null, name, email, phone, address));
        return "redirect:/supplier";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable String id, Model model) {
        model.addAttribute("supplier", supplierService.findById(id));
        return "supplier/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam String supplierId, @RequestParam String name,
                       @RequestParam String email, @RequestParam String phone,
                       @RequestParam String address) {
        supplierService.update(new Supplier(supplierId, name, email, phone, address));
        return "redirect:/supplier";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        supplierService.delete(id);
        return "redirect:/supplier";
    }
}
