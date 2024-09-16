package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class MyModel {
    private String id;
    private String name;

    private double latitude;

    private double longitude;
    private ArrayList<String> imageUrls;

    public MyModel(String id, String name, Double latitude, Double longitude, ArrayList<String> imageUrls) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrls = imageUrls;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude(){return longitude; }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}
