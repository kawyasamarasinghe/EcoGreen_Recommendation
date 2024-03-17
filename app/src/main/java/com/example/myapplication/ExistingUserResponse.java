package com.example.myapplication;

import java.util.List;

public class ExistingUserResponse {
    // No @SerializedName needed here since the recommendations are directly in the array
    private List<Recommendation> recommendations;

    // Getters
    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    // Setters
    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}
