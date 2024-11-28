package com.vti.specification.doctor;


import com.vti.entity.Doctor;
import com.vti.form.doctor.DoctorFitterForm;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DoctorSpecialization {
    public static Specification<Doctor> buildSpec (DoctorFitterForm form){
        return form == null ? null : (Specification<Doctor>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            String search = form.getFullName();
            if (StringUtils.hasText(search)) {
                String patten = "%" + search.trim() + "%";
                Predicate hasNameLike = criteriaBuilder.like(root.get("fullName"), "%" + patten + "%");
                predicates.add(hasNameLike);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
