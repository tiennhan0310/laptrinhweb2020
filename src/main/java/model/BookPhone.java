package model;

import java.sql.Date;
import java.util.ArrayList;


public class BookPhone {


    private Customer customer;
    private ArrayList<PhoneModel> listPhone;
    private boolean status;
    private Date dateCreate;
    private String address;
    private String phone;
    private int id;

    public BookPhone() {

    }

    public BookPhone(Customer customer, ArrayList<PhoneModel> listPhone, boolean status, Date dateCreate,
                     String address, String phone, int id) {
        super();
        this.customer = customer;
        this.listPhone = listPhone;
        this.status = status;
        this.dateCreate = dateCreate;
        this.address = address;
        this.phone = phone;
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<PhoneModel> getListPhone() {
        return listPhone;
    }

    public void setListPhone(ArrayList<PhoneModel> listPhone) {
        this.listPhone = listPhone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BookPhone [customer=" + customer + ", listPhone=" + listPhone + ", status=" + status + ", dateCreate="
                + dateCreate + ", address=" + address + ", phone=" + phone + ", id=" + id + "]";
    }




}
