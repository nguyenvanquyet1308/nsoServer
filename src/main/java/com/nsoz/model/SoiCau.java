package com.nsoz.model;


import java.sql.Timestamp;
import java.util.ArrayList;

public class SoiCau {
    public String ketqua;

    public String soramdom;

    public String time;

    public static ArrayList<SoiCau> soicau = new ArrayList<>();

    public SoiCau(String name, String tong) {
        this.ketqua = name;
        this.soramdom = tong;
    }

    public static void clear() {
        soicau = new ArrayList<>();
    }
}
