package com.droidfreshsquad.poly2023.datve.SaveNumber;

import android.os.Parcel;
import android.os.Parcelable;

public class Number implements Parcelable {
    private int numberLon;
    private int numberTreEm;
    private int numberEmBe;

    public Number() {
        // Hàm khởi tạo trống được yêu cầu cho Firebase
    }

    public Number(int numberLon, int numberTreEm, int numberEmBe) {
        this.numberLon = numberLon;
        this.numberTreEm = numberTreEm;
        this.numberEmBe = numberEmBe;
    }

    public int getNumberLon() {
        return numberLon;
    }

    public void setNumberLon(int numberLon) {
        this.numberLon = numberLon;
    }

    public int getNumberTreEm() {
        return numberTreEm;
    }

    public void setNumberTreEm(int numberTreEm) {
        this.numberTreEm = numberTreEm;
    }

    public int getNumberEmBe() {
        return numberEmBe;
    }

    public void setNumberEmBe(int numberEmBe) {
        this.numberEmBe = numberEmBe;
    }

    protected Number(Parcel in) {
        numberLon = in.readInt();
        numberTreEm = in.readInt();
        numberEmBe = in.readInt();
    }

    public static final Creator<Number> CREATOR = new Creator<Number>() {
        @Override
        public Number createFromParcel(Parcel in) {
            return new Number(in);
        }

        @Override
        public Number[] newArray(int size) {
            return new Number[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberLon);
        dest.writeInt(numberTreEm);
        dest.writeInt(numberEmBe);
    }
}
