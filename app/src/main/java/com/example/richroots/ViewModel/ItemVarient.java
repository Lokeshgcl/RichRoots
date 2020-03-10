package com.example.richroots.ViewModel;

public class ItemVarient {
    private String Variety;
    private String SubVariety;
    private String Grade;
    private String ImageUrl;
    private float MinPrice;
    private float MaxPrice;

    public String getVariety() {
        return Variety;
    }

    public void setVariety(String variety) {
        Variety = variety;
    }

    public String getSubVariety() {
        return SubVariety;
    }

    public void setSubVariety(String subVariety) {
        SubVariety = subVariety;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public float getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(float minPrice) {
        MinPrice = minPrice;
    }

    public float getMaxPrice() {
        return MaxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        MaxPrice = maxPrice;
    }
}
