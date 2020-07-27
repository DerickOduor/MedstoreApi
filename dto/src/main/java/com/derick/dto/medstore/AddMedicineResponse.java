package com.derick.dto.medstore;

public class AddMedicineResponse {

    private ViewMedicineDto medicineDto;

    private String Response;

    public ViewMedicineDto getMedicineDto() {
        return medicineDto;
    }

    public void setMedicineDto(ViewMedicineDto medicineDto) {
        this.medicineDto = medicineDto;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
