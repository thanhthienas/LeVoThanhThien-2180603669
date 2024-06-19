package com.hutech.demo.service;

import com.hutech.demo.model.PhongBan;
import com.hutech.demo.repository.PhongBanRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongBanRepository;
    public List<PhongBan> getAllPhongBan() {
        return phongBanRepository.findAll();
    }
    public Optional<PhongBan> getPhongBanById(String id) {
        return phongBanRepository.findById(id);
    }
    public void addPhongBan(PhongBan phongBan) {
        phongBanRepository.save(phongBan);
    }
    public void updatePhongBan(@NotNull PhongBan phongBan) {
        PhongBan existingCategory = phongBanRepository.findById(phongBan.getMaPhong())
                .orElseThrow(() -> new IllegalStateException("Category with ID " +
                        phongBan.getMaPhong() + " does not exist."));
        existingCategory.setName(phongBan.getName());
        phongBanRepository.save(existingCategory);
    }
    public void deleteCategoryById(String id) {
        if (!phongBanRepository.existsById(id)) {
            throw new IllegalStateException("Phòng ban với mã " + id + " không tồn tại.");
        }
        phongBanRepository.deleteById(id);
    }
}