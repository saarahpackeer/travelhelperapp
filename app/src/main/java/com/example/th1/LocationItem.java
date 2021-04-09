package com.example.th1;

public class LocationItem {
    private String mCity;
    private int cityImage;

    public LocationItem(String mCity, int cityImage){
        this.mCity = mCity;
        this.cityImage = cityImage;
    }

    public void setmCity(String selected){
        this.mCity = selected;
    }

    public String getmCity(){
        return this.mCity;
    }

    public int getCityImage(){
        return cityImage;
    }

}
