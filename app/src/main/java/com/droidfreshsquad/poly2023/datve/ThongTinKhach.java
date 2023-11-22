package com.droidfreshsquad.poly2023.datve;

import android.os.Parcel;
import android.os.Parcelable;

public class ThongTinKhach implements Parcelable {

    public String ten;
    public String ngaySinh;



    public ThongTinKhach(String ten, String ngaySinh, String emailValue, String phoneValue, int tongGiaTien, String diemDi, String diemDen, String gio1, String gio2, String ngay, String san1, String san2, String ari1) {
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTen() {
        return ten;
    }


    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public int getTien() {
        return tien;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public String getGio1() {
        return gio1;
    }

    public String getGio2() {
        return gio2;
    }

    public String getNgay() {
        return ngay;
    }

    public String getSan1() {
        return san1;
    }

    public String getSan2() {
        return san2;
    }

    public String getAri1() {
        return ari1;
    }

    public String getEmail() {
        return email;
    }

    public String email;
    public String soDienThoai;
    public int tien;
    public String diemDi;
    public String diemDen;
    public String gio1;
    public String gio2;
    public String ngay;
    public String san1;
    public String san2;
    public String ari1;
    public String timebay;

    // Constructor không tham số (quan trọng để Firebase có thể đọc dữ liệu)
    public ThongTinKhach() {
        // No-argument constructor
    }
    public ThongTinKhach(String ten, String ngaySinh, String emailValue, String phoneValue, int tongGiaTien, String diemDi, String diemDen, String gio1, String gio2, String ngay, String san1, String san2, String ari1, String timebay) {
        // Constructor sử dụng để khởi tạo đối tượng

        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.email = emailValue;  // Use the provided parameter
        this.soDienThoai = phoneValue;  // Use the provided parameter
        this.tien = tongGiaTien;  // Use the provided parameter
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.gio1 = gio1;
        this.gio2 = gio2;
        this.ngay = ngay;
        this.san1 = san1;
        this.san2 = san2;
        this.ari1 = ari1;
        this.timebay = timebay;
    }

    // Các phương thức getter và setter

    // Các phương thức Parcelable
    protected ThongTinKhach(Parcel in) {
        ten = in.readString();
        ngaySinh = in.readString();
        email = in.readString();
        soDienThoai = in.readString();
        tien = in.readInt();
        diemDi = in.readString();
        diemDen = in.readString();
        gio1 = in.readString();
        gio2 = in.readString();
        ngay = in.readString();
        san1 = in.readString();
        san2 = in.readString();
        ari1 = in.readString();
        timebay = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ten);
        dest.writeString(ngaySinh);
        dest.writeString(email);
        dest.writeString(soDienThoai);
        dest.writeInt(tien);
        dest.writeString(diemDi);
        dest.writeString(diemDen);
        dest.writeString(gio1);
        dest.writeString(gio2);
        dest.writeString(ngay);
        dest.writeString(san1);
        dest.writeString(san2);
        dest.writeString(ari1);
        dest.writeString(timebay);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ThongTinKhach> CREATOR = new Creator<ThongTinKhach>() {
        @Override
        public ThongTinKhach createFromParcel(Parcel in) {
            return new ThongTinKhach(in);
        }

        @Override
        public ThongTinKhach[] newArray(int size) {
            return new ThongTinKhach[size];
        }
    };

    public void setPaymentMethod(String tiềnMặt) {
    }
}
