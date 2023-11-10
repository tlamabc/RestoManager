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
    private String price;
    private String DiemDi;
    private String DiemDen;
    private String sanbaydi;
    private String sanbayden;

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
    public String getDate() {
        return date;
    }
    public String getPrice() {
        return price;
    }
    public String getDiemDi() {
        return DiemDi;
    }
    public String getDiemDen() {
        return DiemDen;
    }
    public String getSanbaydi() {return sanbaydi;}
    public String getSanbayden() {return sanbayden;}

    // Parcelable implementation
    protected Ticket(Parcel in) {
        Airlines = in.readString();
        Scheduled = in.readString();
        date = in.readString();
        id = in.readInt();
        Scheduled2 = in.readString();
        name_ticket = in.readString();
        price = in.readString();
        DiemDi = in.readString();
        DiemDen = in.readString();
        sanbaydi = in.readString();
        sanbayden = in.readString();
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
        dest.writeString(Airlines);
        dest.writeString(Scheduled);
        dest.writeString(date);
        dest.writeInt(id);
        dest.writeString(Scheduled2);
        dest.writeString(name_ticket);
        dest.writeString(price);
        dest.writeString(DiemDi);
        dest.writeString(DiemDen);
        dest.writeString(sanbayden);
        dest.writeString(sanbaydi);
    }
}
