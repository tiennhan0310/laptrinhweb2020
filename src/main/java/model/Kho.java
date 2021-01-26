package model;

public class Kho {

    private PhoneModel phone;
    private int soLuongDaBan;

    public Kho() {

    }

    public PhoneModel getPhone() {
        return phone;
    }

    public void setPhone(PhoneModel phone) {
        this.phone = phone;
    }

    public int getSoLuongDaBan() {
        return soLuongDaBan;
    }

    public void setSoLuongDaBan(int soLuongDaBan) {
        this.soLuongDaBan = soLuongDaBan;
    }

    @Override
    public String toString() {
        return "Kho [phone=" + phone + ", soLuongDaBan=" + soLuongDaBan + "]";
    }


}
