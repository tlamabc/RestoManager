package com.droidfreshsquad.poly2023.datve;

import android.os.Parcel;
import android.os.Parcelable;

public class Ticket implements Parcelable {
    private int id;
    private String Airlines;
    private String Scheduled;
    private String Scheduled2;
    private String date;
    private String name_ticket;
    private int price;
    private String DiemDi;
    private String DiemDen;
    private String sanbaydi;
    private String sanbayden;
    private String timebay;


    public void setDiemDi(String diemDi) {
        this.DiemDi = diemDi;
    }

    public void setDiemDen(String diemDen) {
        this.DiemDen = diemDen;
    }

    public void setScheduled(String scheduled) {
        this.Scheduled = scheduled;
    }

    public void setScheduled2(String scheduled2) {
        this.Scheduled2 = scheduled2;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSanbaydi(String sanbaydi) {
        this.sanbaydi = sanbaydi;
    }

    public void setSanbayden(String sanbayden) {
        this.sanbayden = sanbayden;
    }

    public void setAirlines(String airlines) {
        this.Airlines = airlines;
    }

    public void setTimebay(String timebay) {
        this.timebay = timebay;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Empty constructor for Firebase
    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public String getName_ticket() {
        return name_ticket;
    }

    public String getScheduled2() {
        return Scheduled2;
    }

    public String getAirlines() {
        return Airlines;
    }

    public String getScheduled() {
        return Scheduled;
    }

    public String getTimebay() {
        return timebay;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public String getDiemDi() {
        return DiemDi;
    }

    public String getDiemDen() {
        return DiemDen;
    }

    public String getSanbaydi() {
        return sanbaydi;
    }

    public String getSanbayden() {
        return sanbayden;
    }

    // Parcelable implementation
    protected Ticket(Parcel in) {
        id = in.readInt();
        Airlines = in.readString();
        Scheduled = in.readString();
        Scheduled2 = in.readString();
        date = in.readString();
        name_ticket = in.readString();
        price = in.readInt();
        DiemDi = in.readString();
        DiemDen = in.readString();
        sanbaydi = in.readString();
        sanbayden = in.readString();
        timebay = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(Airlines);
        dest.writeString(Scheduled);
        dest.writeString(Scheduled2);
        dest.writeString(date);
        dest.writeString(name_ticket);
        dest.writeInt(price);
        dest.writeString(DiemDi);
        dest.writeString(DiemDen);
        dest.writeString(sanbaydi);
        dest.writeString(sanbayden);
        dest.writeString(timebay);
    }
}
