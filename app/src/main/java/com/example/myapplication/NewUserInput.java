package com.example.myapplication;
import com.google.gson.annotations.SerializedName;

public class NewUserInput {
    @SerializedName("Plastic Type")
    private String plasticType;
    @SerializedName("Item Location")
    private String itemLocation;
    @SerializedName("Industry")
    private String industry;

    // Getters
    public String getPlasticType() {
        return plasticType;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public String getIndustry() {
        return industry;
    }

    // Setters
    public void setPlasticType(String plasticType) {
        this.plasticType = plasticType;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
