package com.vti.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name = "doctor_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @OneToOne
    @JoinColumn(name = "user_Id", referencedColumnName = "user_Id", nullable = false)
    private User user;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "experience_years", nullable = false)
    private int experienceYears;

    @Column(name = "education", nullable = false)
    private String education;

    @Column(name = "certifications", nullable = false)
    private String certifications;
}
