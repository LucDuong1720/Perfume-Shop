package model;

import service.IModel;
import utils.DateUtils;

import java.util.Date;

public class User implements IModel<User> {
    private long id;
    private String nameAccount;
    private String password;
    private String nameUser;
    private String phone;
    private String address;
    private Role role;

    public User(){}

    public User(long id, String nameAccount, String password, String nameUser, String phone, String address, Role role) {
        this.id = id;
        this.nameAccount = nameAccount;
        this.password = password;
        this.nameUser = nameUser;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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

    @Override
    public User parseData(String line) {
        User user = new User();
        String[] items = line.split(",");
        user.setId(Long.parseLong(items[0]));
        user.setNameAccount(items[1]);
        user.setPassword(items[2]);
        user.setNameUser(items[3]);
        user.setPhone(items[4]);
        user.setAddress(items[5]);
        user.role = Role.parseRole(items[6]);

        return user;
    }

    @Override
    public String toString() {
        return id + "," +
                nameAccount + "," +
                password + "," +
                nameUser + "," +
                phone + "," +
                address + "," +
                role
                ;
    }
}

