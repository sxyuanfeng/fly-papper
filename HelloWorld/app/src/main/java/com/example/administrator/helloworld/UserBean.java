package com.example.administrator.helloworld;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2019/1/1.
 */
public class UserBean {
    @SerializedName("id")
    private int id;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    @SerializedName("number")
    private String number;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
