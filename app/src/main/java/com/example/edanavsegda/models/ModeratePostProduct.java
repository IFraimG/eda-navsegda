package com.example.edanavsegda.models;

import java.util.ArrayList;

public class ModeratePostProduct extends PostProduct {
    public ArrayList<Product> productList;

    public ModeratePostProduct(String title, String description, String location) {
        super(title, description, location);
    }
}
