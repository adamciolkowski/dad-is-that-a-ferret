package com.forfun.dadisthataferret.model;

public class Animal {

    private final String name;
    private final String imageUrl;

    public Animal(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
