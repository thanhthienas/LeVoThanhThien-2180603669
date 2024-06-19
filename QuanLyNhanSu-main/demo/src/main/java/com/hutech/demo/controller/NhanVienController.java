package com.hutech.demo.controller;

import com.hutech.demo.model.NhanVien;
import com.hutech.demo.service.NhanVienService;
import com.hutech.demo.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/NhanVien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;
    // Display a list of all NhanVien
    @GetMapping("/NhanVien")
    public String showNhanVienList(Model model) {
        model.addAttribute("NhanVien", nhanVienService.getAllNhanVien());
        return "/NhanVien/dsNhanVien";
    }
    // For adding a new NhanVien
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("NhanVien", new NhanVien());
        model.addAttribute("PhongBan", phongBanService.getAllPhongBan()); //
        return "/NhanVien/ThemNV";
    }
    // Process the form for adding a new NhanVien
    @PostMapping("/add")
    public String addNhanVien(@Valid NhanVien nhanVien, BindingResult result) {
        if (result.hasErrors()) {
            return "/NhanVien/ThemNV";
        }
        nhanVienService.addProduct(nhanVien);
        return "redirect:/NhanVien/NhanVien";
    }
    // For editing a NhanVien
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String MaNV, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(MaNV)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + MaNV));
        model.addAttribute("NhanVien", nhanVien);
        model.addAttribute("PhongBan", phongBanService.getAllPhongBan());
        return "/NhanVien/SuaTT";
    }

    @PostMapping("/edit/{id}")
    public String updateNhanVien(@PathVariable("id") String MaNV, @Valid NhanVien nhanVien,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            nhanVien.setMaNV(MaNV); // Preserve the ID in case of errors
            model.addAttribute("PhongBan", phongBanService.getAllPhongBan());
            return "/NhanVien/SuaTT";
        }
        nhanVienService.updateNhanVien(nhanVien);
        return "redirect:/NhanVien";
    }
    // Handle request to delete a NhanVien
    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable String id) {
        nhanVienService.deleteNhanVienById(id);
        return "redirect:/NhanVien/NhanVien";
    }
}
