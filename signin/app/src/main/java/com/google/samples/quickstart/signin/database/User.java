package com.google.samples.quickstart.signin.database;

/**
 * Created by brayg_000 on 09/08/2016.
 */

public class User {
    private int id;
    private String name;
    private String address;

    public User() {
    }

    public User(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
