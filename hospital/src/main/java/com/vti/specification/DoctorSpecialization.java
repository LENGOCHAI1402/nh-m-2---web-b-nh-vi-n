package com.vti.specification;

import com.vti.Entity.Doctor;
import com.vti.Form.DoctorFitterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
