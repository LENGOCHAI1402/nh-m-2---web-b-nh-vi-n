package com.vti.Form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientFilterForm {
    private Long userId;               // Lọc theo userId
    private String medicalHistory;     // Lọc theo tiền sử bệnh
    private String insuranceNumber;    // Lọc theo số bảo hiểm
    private String allergies;          // Lọc theo dị ứng
    private String bloodType;          // Lọc theo nhóm máu
    private Integer minWeight;         // Lọc theo cân nặng tối thiểu
    private Integer maxWeight;         // Lọc theo cân nặng tối đa
    private Integer minHeight;         // Lọc theo chiều cao tối thiểu
    private Integer maxHeight;
}
