package com.example.volleydemo;


import java.io.Serializable;

public class MainData implements Serializable {



    // initialize variable
    private String name,image;

    // generate getter and setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
