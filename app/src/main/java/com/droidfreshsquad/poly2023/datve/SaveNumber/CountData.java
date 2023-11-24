package com.droidfreshsquad.poly2023.datve.SaveNumber;

public class CountData {
    private static CountData instance;
    private int count;

    private CountData() {
        // Hàm khởi tạo trống được yêu cầu cho Firebase
    }

    public static synchronized CountData getInstance() {
        if (instance == null) {
            instance = new CountData();
        }
        return instance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
