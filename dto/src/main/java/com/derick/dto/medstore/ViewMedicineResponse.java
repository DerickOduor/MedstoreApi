package com.derick.dto.medstore;

import java.util.List;

public class ViewMedicineResponse {
    private String Response;
    private List<ViewMedicineDto> medicineList;
    private ViewMedicineDto medicine;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public List<ViewMedicineDto> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<ViewMedicineDto> medicineList) {
        this.medicineList = medicineList;
    }

    public ViewMedicineDto getMedicine() {
        return medicine;
    }

    public void setMedicine(ViewMedicineDto medicine) {
        this.medicine = medicine;
    }
}
