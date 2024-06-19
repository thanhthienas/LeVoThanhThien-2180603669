package com.hutech.demo.controller;

import com.hutech.demo.model.PhongBan;
import com.hutech.demo.service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class PhongBanController {
    @Autowired
    private final PhongBanService phongBanService;
    @GetMapping("/PhongBan/add")
    public String showAddForm(Model model) {
        model.addAttribute("PhongBan", new PhongBan());
        return "/PhongBan/ThemPhongBan";
    }
    @PostMapping("/PhongBan/add")
    public String addPhongBan(@Valid PhongBan phongBan, BindingResult result) {
        if (result.hasErrors()) {
            return "/PhongBan/ThemPhongBan";
        }
        phongBanService.addPhongBan(phongBan);
        return "redirect:/PhongBan";
    }
    // Hiển thị danh sách PhongBan
    @GetMapping("/PhongBan")
    public String listPhongBan(Model model) {
        List<PhongBan> phongBan = phongBanService.getAllPhongBan();
        model.addAttribute("PhongBan", phongBan);
        return "/PhongBan/DanhSachPhongBan";
    }
    // GET request to show PhongBan edit form
    @GetMapping("/PhongBan/edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        PhongBan phongBan = phongBanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        model.addAttribute("PhongBan", phongBan);
        return "/PhongBan/SuaPhongBan";
    }
    // POST request to update PhongBan
    @PostMapping("/PhongBan/edit/{id}")
    public String updatePhongBan(@PathVariable("id") String id, @Valid PhongBan phongBan,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            phongBan.setMaPhong(id);
            return "/PhongBan/SuaPhongBan";
        }
        phongBanService.updatePhongBan(phongBan);
        model.addAttribute("PhongBan", phongBanService.getAllPhongBan());
        return "redirect:/PhongBan";
    }
    // GET request for deleting PhongBan
    @GetMapping("/PhongBan/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id, Model model) {
        PhongBan phongBan = phongBanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        phongBanService.deleteCategoryById(id);
        model.addAttribute("PhongBan", phongBanService.getAllPhongBan());
        return "redirect:/PhongBan";
    }
}