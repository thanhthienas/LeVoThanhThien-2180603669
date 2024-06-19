package com.hutech.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PhongBan")
public class PhongBan {
    @Id
    @Column(length = 2)
    private String MaPhong;
    @NotBlank(message = "Tên là bắt buộc")
    @Column(length = 30)
    private String name;
}