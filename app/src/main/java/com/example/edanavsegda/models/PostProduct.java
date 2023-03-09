package com.example.edanavsegda.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class PostProduct extends Product {
    public String title;
    protected String userID;
    protected String postID;
    public String description;
    // bitmap
    public int[] imageListSrc;
    public String location;
    public Date dateSending;
    public String typeOfStorage = "none";
    public int storageID = 0;

    public PostProduct(String title) {
        super(title);
        this.title = title;
    }

    public PostProduct(String title, String description, String location) {
        super(title);
        this.title = title;
        this.description = description;
        this.location = location;
        this.dateSending = new Date();

        this.userID = UUID.randomUUID().toString();
        this.postID = UUID.randomUUID().toString();
    }

    public void setImages(int[] imageListSrc) {
        this.imageListSrc = new int[imageListSrc.length];
        for (int i = 0; i < imageListSrc.length; i++) this.imageListSrc[i] = imageListSrc[i];
    }

    public void addProductToStorageByPos(int storageID) {
        this.storageID = storageID;
        switch (storageID) {
            case 1:
                this.typeOfStorage = "холодильник";
                break;
            case 2:
                this.typeOfStorage = "морозилка";
                break;
            case 3:
                this.typeOfStorage = "кладовая";
                break;
            default:
                this.typeOfStorage = "";
        }
    }
}
