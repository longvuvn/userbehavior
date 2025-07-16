package com.example.userbehaviormanagement.entities;

import com.example.userbehaviormanagement.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "\"users\"")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Auditing{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Size(min = 3, max = 255, message = "Tên phải từ 3 đến 255 ký tự")
    @NotBlank(message = "Tên không được để trống")
    private String fullName;


    @Email(message = "Email không hợp lệ")
    @Column(nullable = false, unique = true)
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|vn)$",
            message = "Email phải kết thúc bằng .com hoặc .vn"
    )
    @NotBlank(message = "Email không được để trống")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Size(min = 8, max = 255, message = "Mật khẩu phải có ít nhất 8 kí tự")
    private String password;

    @Column(nullable = false)
    private String avatar;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserSession>sessions;
}
