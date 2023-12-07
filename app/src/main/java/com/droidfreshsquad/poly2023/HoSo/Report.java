package com.droidfreshsquad.poly2023.HoSo;

import java.util.Date;

public class Report {
    private String baocao;
    private String email;
    private long timestamp;
    public Report() {
        // Default constructor required for calls to DataSnapshot.getValue(Report.class)
    }

    public Report(String issueType,String email) {
        this.baocao = issueType;
        this.email = email;
        this.timestamp = new Date().getTime();
    }
    public String getEmail() {
        return email;
    }
    public String getBaocao() {
        return baocao;
    }
    public long getTimestamp() {
        return timestamp;
    }
}
