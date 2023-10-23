package com.droidfreshsquad.poly2023.datve;

public class Ticket {
    private String Airlines;
    private String Scheduled;
    private String date;
    private int id;
    private String name_ticket;
    private String price;

    public Ticket() {
        // Empty constructor for Firebase
    }

    // Getter methods for the fields
    public String getAirlines() {
        return Airlines;
    }

    public String getScheduled() {
        return Scheduled;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getName_ticket() {
        return name_ticket;
    }

    public String getPrice() {
        return price;
    }
}
