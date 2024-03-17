package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RecommendationAPI {

    @POST("/recommendations/existing")
    Call<ExistingUserResponse> getRecommendationsForExistingUser(@Body UserIdInput input);

    @POST("/recommendations/new")
    Call<List<Recommendation>> getRecommendationsForNewUser(@Body NewUserInput input);
}
