package com.sharmadairy.model;

public class Customer {
    String name,mobile,shift,status,address,quantity,startDate;

    public Customer(String name,
                    String mobile,
                    String shift,
                    String status,
                    String address,
                    String quantity,
                    String startDate) {
        this.name = name;
        this.mobile = mobile;
        this.shift = shift;
        this.status = status;
        this.address = address;
        this.quantity = quantity;
        this.startDate = startDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
