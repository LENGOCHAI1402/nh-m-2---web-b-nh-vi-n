package com.vti.Repository;

import com.vti.Entity.Doctor;
import com.vti.Entity.Patient;
import com.vti.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p WHERE p.user.fullName LIKE %:fullName%")
    Page<Doctor> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.user = :user")
    Optional<Patient> findByUser(@Param("user") User user);
    List<Patient> findAll();
}
