package com.example.edanavsegda.models;

import java.util.UUID;

public class Product {
    public String name;
    // Bitmap
    public int logo;
    public String expirationDefault;
    public int calories;
    public int weight;
    private String productID;
    public String type;

    public Product(String name) {
        this.name = name;
        this.productID = UUID.randomUUID().toString();
    }

    public Product(String name, String type, int logo, String expirationDefault, int calories, int weight) {
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.expirationDefault = expirationDefault;
        this.calories = calories;
        this.productID = UUID.randomUUID().toString();
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public void setExpirationDefault(String expirationDefault) {
        this.expirationDefault = expirationDefault;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductID() {
        return productID;
    }

    public String getExpiration() {
        return "Предполагаемый срок: " + expirationDefault + " месяцев";
    }

    public void setOriginalID(String productID) {
        this.productID = productID;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
