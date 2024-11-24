package com.vti.Entity;

import com.vti.Entity.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", length = 50, unique = true, nullable = true)
    private String username;

    @Column(name = "email", unique = true, nullable = true)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "full_name", nullable = true)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = true)
    private UserRole role;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "gender", nullable = true)
    private String gender; // Trường giới tính

    @Column(name = "birthday", nullable = true)
    private LocalDate birthday;

    @Column(name = "create_at", nullable = true, updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;
}
