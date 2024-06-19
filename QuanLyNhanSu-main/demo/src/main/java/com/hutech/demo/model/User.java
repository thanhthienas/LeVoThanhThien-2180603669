package com.hutech.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length = 50, unique = true)
    @NotBlank(message = "Hãy nhập Username")
    @Size(min = 1, max = 50, message = "Username phải có kí tự từ 1 đến 50")
            private String username;
            @Column(name = "password", length = 250)
            @NotBlank(message = "Hãy nhập mật khẩu")
            private String password;
            @Column(name = "email", length = 50, unique = true)
            @NotBlank(message = "Hãy nhập Email")
            @Size(min = 1, max = 50, message = "Email phải có kí tự từ 1 đến 50")
    @Email
    private String email;
    @Column(name = "phone", length = 10, unique = true)
    @Length(min = 10, max = 10, message = "Số điện thoại phải có 10 kí tự")
    @Pattern(regexp = "^[0-9]*$", message = "Số điện thoại chỉ có thể là số")
    private String phone;
    @Column(name = "provider", length = 50)
    private String provider;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> userRoles = this.getRoles();
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return
                false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
