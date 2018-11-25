package com.example.filenber.my_new_app;

/**
 * Created by filenber on 31/10/2018.
 */

public class Order_Detail_listitems {
    private int gid;
    private String aid;
    private String gname;
    private String gimages;
    private String desc;
    private String date;
    private String status;

    private String price;

    public Order_Detail_listitems(int gid, String aid, String gname, String gimages, String desc, String date, String status, String price) {
        this.gid = gid;
        this.aid = aid;
        this.gname = gname;
        this.gimages = gimages;
        this.desc = desc;
        this.date = date;
        this.status = status;
        this.price = price;
    }
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGimages() {
        return gimages;
    }

    public void setGimages(String gimages) {
        this.gimages = gimages;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
