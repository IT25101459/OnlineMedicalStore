package com.medicalstore.controller;

import com.medicalstore.model.Medicine;
import com.medicalstore.service.CategoryService;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired private MedicineService medicineService;
    @Autowired private CategoryService categoryService;
    @Autowired private SupplierService supplierService;

    @GetMapping
    public String list(@RequestParam(required = false) String search, Model model) {
        List<Medicine> medicines;
        if (search != null && !search.isEmpty()) {
            medicines = medicineService.search(search);
            model.addAttribute("search", search);
        } else {
            medicines = medicineService.getAll();
        }
        long inStock    = medicines.stream().filter(m -> m.getStock() > 0).count();
        long outOfStock = medicines.stream().filter(m -> m.getStock() == 0).count();

        model.addAttribute("medicines", medicines);
        model.addAttribute("inStockCount", inStock);
        model.addAttribute("outOfStockCount", outOfStock);
        return "medicine/list";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("suppliers", supplierService.getAll());
        return "medicine/add";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam String categoryId,
                      @RequestParam String supplierId,
                      @RequestParam double price,
                      @RequestParam int stock,
                      @RequestParam String description,
                      @RequestParam String expiryDate) {
        Medicine m = new Medicine(null, name, categoryId, supplierId, price, stock, description, expiryDate);
        medicineService.add(m);
        return "redirect:/medicine";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable String id, Model model) {
        model.addAttribute("medicine", medicineService.findById(id));
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("suppliers", supplierService.getAll());
        return "medicine/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam String medicineId,
                       @RequestParam String name,
                       @RequestParam String categoryId,
                       @RequestParam String supplierId,
                       @RequestParam double price,
                       @RequestParam int stock,
                       @RequestParam String description,
                       @RequestParam String expiryDate) {
        Medicine m = new Medicine(medicineId, name, categoryId, supplierId, price, stock, description, expiryDate);
        medicineService.update(m);
        return "redirect:/medicine";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        medicineService.delete(id);
        return "redirect:/medicine";
    }
}
