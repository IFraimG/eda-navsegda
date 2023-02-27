package com.example.edanavsegda.models;

public class StoreFood {
    public String title = "";
    public int logo;
    int id;
    int countProducts = 0;

    public StoreFood(String title, int logo, int id) {
        this.title = title;
        this.logo = logo;
        this.id = id;
    }

    public void setCountProducts(int res) {
        countProducts = res;
    }

    public int getId() {
        return this.id;
    }

    public String getCountProducts() {
        return "Включает в себя " + this.countProducts + " продуктов";
    }
}