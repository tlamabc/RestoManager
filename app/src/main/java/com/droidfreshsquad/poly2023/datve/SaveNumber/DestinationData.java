package com.droidfreshsquad.poly2023.datve.SaveNumber;

public class DestinationData {
    private static DestinationData instance;
    private String diemDi;
    private String diemDen;

    private DestinationData() {
        // Hàm khởi tạo trống được yêu cầu cho Firebase
    }

    public static synchronized DestinationData getInstance() {
        if (instance == null) {
            instance = new DestinationData();
        }
        return instance;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public void setDiemDi(String diemDi) {
        this.diemDi = diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public void setDiemDen(String diemDen) {
        this.diemDen = diemDen;
    }
}