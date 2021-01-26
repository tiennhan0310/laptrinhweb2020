package model;

import java.io.Serializable;
import java.sql.Date;

public class PhoneModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private int id;
    private String name;
    private String typeTel;
    private double price;//long price
    private String nhaSanXuat;
    private String url_img;
    private Date ngaySanXuat;
    private String description;
    private int luotTruyCap;
    private int soLuong;


    public PhoneModel() {
        // TODO Auto-generated constructor stub
    }


    public PhoneModel(int id, String name, String typeTel, double price, String nhaSanXuat, String url_img,
                      Date ngaySanXuat, String description, int luotTruyCap, int soLuong) {
        super();
        this.id = id;
        this.name = name;
        this.typeTel = typeTel;
        this.price = price;
        this.nhaSanXuat = nhaSanXuat;
        this.url_img = url_img;
        this.ngaySanXuat = ngaySanXuat;
        this.description = description;
        this.luotTruyCap = luotTruyCap;
        this.soLuong = soLuong;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getTypeTel() {
        return typeTel;
    }


    public void setTypeTel(String typeTel) {
        this.typeTel = typeTel;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public String getNhaSanXuat() {
        return nhaSanXuat;
    }


    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }


    public String getUrl_img() {
        return url_img;
    }


    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }


    public Date getNgaySanXuat() {
        return ngaySanXuat;
    }


    public void setNgaySanXuat(Date ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public int getLuotTruyCap() {
        return luotTruyCap;
    }


    public void setLuotTruyCap(int luotTruyCap) {
        this.luotTruyCap = luotTruyCap;
    }


    public int getSoLuong() {
        return soLuong;
    }


    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }


    @Override
    public String toString() {
        return "PhoneModel [id=" + id + ", name=" + name + ", typeTel=" + typeTel + ", price=" + price + ", nhaSanXuat="
                + nhaSanXuat + ", url_img=" + url_img + ", ngaySanXuat=" + ngaySanXuat + ", description=" + description
                + ", luotTruyCap=" + luotTruyCap + ", soLuong=" + soLuong + "]";
    }



}
