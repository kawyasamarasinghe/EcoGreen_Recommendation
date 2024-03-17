package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class UserIdInput {
    @SerializedName("user_id") // Match the field name expected by your Flask API
    private String userId;

    // Constructor
    public UserIdInput(String userId) {
        this.userId = userId;
    }

    // Getter
    public String getUserId() {
        return userId;
    }

    // Setter
    public void setUserId(String userId) {
        this.userId = userId;
    }
}