package com.vti.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @Column(name = "treatment", nullable = false)
    private String treatment;

    @Column(name = "prescription")
    private String prescription;

    @Column(name = "notes")
    private String notes;

    @Column(name = "bed_id")
    private int bedId;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood", nullable = false)
    private BloodType blood;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;


}
