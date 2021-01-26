package model;

import java.io.Serializable;

public class Customer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int customerId;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Customer() {

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", address=" + address + "]";
    }


}
