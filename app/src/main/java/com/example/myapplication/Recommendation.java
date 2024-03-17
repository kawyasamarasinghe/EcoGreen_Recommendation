package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Recommendation {
    @SerializedName("Post ID")
    private String postId;
    @SerializedName("Plastic Type")
    private String plasticType;
    @SerializedName("Item Location")
    private String itemLocation;
    @SerializedName("Quantity")
    private double quantity;
    @SerializedName("Starting Price")
    private double startingPrice;

    // Getters
    public String getPostId() {
        return postId;
    }

    public String getPlasticType() {
        return plasticType;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    // Setters
    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setPlasticType(String plasticType) {
        this.plasticType = plasticType;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }
}


