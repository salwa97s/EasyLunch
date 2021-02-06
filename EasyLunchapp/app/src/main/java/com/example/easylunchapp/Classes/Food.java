package com.example.easylunchapp.Classes;

public class Food {
    private String FoodName;
    private String Price ;
    private  int Picture ;

    public Food(){}

    public Food(String foodName, String price, int picture) {
        FoodName = foodName;
        Price = price;
        Picture = picture;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }
}
