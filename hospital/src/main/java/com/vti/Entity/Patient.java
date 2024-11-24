package com.vti.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(name = "user_id", nullable = false,insertable = false,updatable = false)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_Id", referencedColumnName = "user_Id", nullable = false)
    private User user;

    @Column(name = "medical_histoy")
    private String medicalHistory;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "weight")
    private int weight;

    @Column(name = "height")
    private int height;
}
