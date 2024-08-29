package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class MyModel {
    private String name;
    private int description;
    private ArrayList<String> imageUrls;

    public MyModel(String name, int description, ArrayList<String> imageUrls) {
        this.name = name;
        this.description = description;
        this.imageUrls = imageUrls;
    }

    public String getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}
