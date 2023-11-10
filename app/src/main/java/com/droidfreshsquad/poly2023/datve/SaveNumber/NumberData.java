package com.droidfreshsquad.poly2023.datve.SaveNumber;
public class NumberData {
    private static NumberData instance;
    private Number numberObject;

    private NumberData() {
        // Hàm khởi tạo trống được yêu cầu cho Firebase
    }

    public static synchronized NumberData getInstance() {
        if (instance == null) {
            instance = new NumberData();
        }
        return instance;
    }

    public Number getNumberObject() {
        return numberObject;
    }

    public void setNumberObject(Number numberObject) {
        this.numberObject = numberObject;
    }
}
