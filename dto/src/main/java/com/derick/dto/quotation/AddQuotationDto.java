package com.derick.dto.quotation;

import java.util.List;

public class AddQuotationDto {
    private int PrescriptionId;
    private List<Integer> MedicineIds;
    private boolean Status;

    public int getPrescriptionId() {
        return PrescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        PrescriptionId = prescriptionId;
    }

    public List<Integer> getMedicineIds() {
        return MedicineIds;
    }

    public void setMedicineIds(List<Integer> medicineIds) {
        MedicineIds = medicineIds;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
