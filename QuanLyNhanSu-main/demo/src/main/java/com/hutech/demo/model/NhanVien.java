package com.hutech.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @Column(length = 3)
    private String MaNV;
    @NotBlank(message = "Tên là bắt buộc")
    @Column(length = 100)
    private String tenNV;
    @Column(length = 3)
    private String Phai;
    @Column(length = 200)
    private String NoiSinh;
    @ManyToOne
    @JoinColumn(name = "PhongBan_id")
    private PhongBan pb;
    private int Luong;
}