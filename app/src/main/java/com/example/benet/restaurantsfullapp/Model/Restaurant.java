package com.example.benet.restaurantsfullapp.Model;

import java.io.Serializable;

/**
 * Created by Benet on 26/02/15.
 */
public class Restaurant implements Serializable{

    private String name, city, country, url;
    private int img;


    public Restaurant(String name, String country, String city, int img, String url) {
        this.name = name;
        this.img = img;
        this.url = url;
        this.country = country;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", url='" + url + '\'' +
                ", img=" + img +
                '}';
    }
}
