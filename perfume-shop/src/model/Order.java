package model;

import service.IModel;
import utils.DateUtils;

import java.util.Date;

public class Order implements IModel<Order> {
    private long id;
    private String fullName;
    private String phone;
    private String address;
    private double grandTotal;
    private Date creatAt;

    public Order() {}

    public Order(long id, String fullName, String phone, String address, double grandTotal, Date creatAt) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.grandTotal = grandTotal;
        this.creatAt = creatAt;
    }

    public Order(long id, String fullName, String phone, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", this.id, this.fullName, this.phone, this.address,this.grandTotal,
                DateUtils.dateToString(this.creatAt));
    }

    @Override
    public Order parseData(String line) {
        Order order = new Order();
        String[] items = line.split(",");
        order.setId(Long.parseLong(items[0]));
        order.setFullName(items[1]);
        order.setPhone(items[2]);
        order.setAddress(items[3]);
        order.setGrandTotal(Double.parseDouble(items[4]));
        order.setCreatAt(DateUtils.parseDate(items[5]));

        return order;
    }
}