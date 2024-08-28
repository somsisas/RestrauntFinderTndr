package com.example.myapplication;

public class MyModel {
    private String name;
    private int description;
    private String imageUrl;

    public MyModel(String name, int description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }


}
