package com.vti.repository;

import com.vti.entity.Doctor;
import com.vti.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {
    @Query("SELECT d FROM Doctor d WHERE d.user.fullname LIKE %:fullName%")

    Page<Doctor> findByFullName(@Param("fullName") String fullName, Pageable pageable);

    @Query("SELECT d FROM Doctor d WHERE d.user = :user")

    Optional<Doctor> findByUser(@Param("user") User user);
    List<Doctor> findAll();

}

