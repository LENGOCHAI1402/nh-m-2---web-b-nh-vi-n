package com.vti.Service;

import com.vti.Dto.MedicalRecordDto;
import com.vti.Form.CreateRecordForm;

public interface MedicalRecordService {
    MedicalRecordDto createRecord(CreateRecordForm form);
}
