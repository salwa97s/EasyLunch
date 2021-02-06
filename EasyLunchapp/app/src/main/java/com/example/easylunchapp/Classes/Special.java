package com.example.easylunchapp.Classes;

public class Special {

    private int Image ;
    private String Title  , Price ;

    public Special(){}

    public Special(int image,String title , String price) {
        Image = image;
        Title = title;
        Price =  price ;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
