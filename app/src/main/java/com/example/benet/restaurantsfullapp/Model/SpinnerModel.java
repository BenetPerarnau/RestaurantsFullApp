package com.example.benet.restaurantsfullapp.Model;

/**
 * Created by Benet on 28/02/15.
 */
public class SpinnerModel {

    private int img;
    private String name;

    public SpinnerModel(int img, String name){

        setImg(img);
        setName(name);
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
