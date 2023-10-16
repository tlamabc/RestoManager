package com.droidfreshsquad.poly2023.Domain;

public class ExploreDomain {
    private String pic;
    private String title;
    private  String title1;

    public ExploreDomain(String pic, String title, String title1) {
        this.pic = pic;
        this.title = title;
        this.title1 = title1;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }
}
