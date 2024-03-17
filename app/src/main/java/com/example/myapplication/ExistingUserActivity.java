package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// ... (Import your data structures and API interface) ...

public class ExistingUserActivity extends AppCompatActivity {

    private EditText userIdInput;
    private Button getRecommendationsButton;
    private TextView recommendationsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_user);

        userIdInput = findViewById(R.id.userIdInput);
        getRecommendationsButton = findViewById(R.id.getRecommendationsButton);
        recommendationsTextView = findViewById(R.id.recommendationsTextView);

        getRecommendationsButton.setOnClickListener(this::fetchRecommendations);
    }

    private void fetchRecommendations(View view) {
        String userId = userIdInput.getText().toString();

        // Create UserIdInput Object
        UserIdInput input = new UserIdInput(userId);

        RecommendationAPI api = NetworkClient.getRetrofitInstance().create(RecommendationAPI.class);
        Call<ExistingUserResponse> call = api.getRecommendationsForExistingUser(input);

        call.enqueue(new Callback<ExistingUserResponse>() {
            @Override
            public void onResponse(Call<ExistingUserResponse> call, Response<ExistingUserResponse> response) {
                Log.d("MyApp", "Request URL: " + call.request().url()); // Log the exact URL

                if (response.isSuccessful()) {
                    List<Recommendation> recommendations = response.body().getRecommendations();
                    displayRecommendations(recommendations);
                } else {
                    Toast.makeText(ExistingUserActivity.this, "Error fetching recommendations", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ExistingUserResponse> call, Throwable t) {
                Log.e("MyApp", "Network Failure", t); // Log the error with stack trace
                Toast.makeText(ExistingUserActivity.this, "Network Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void displayRecommendations(List<Recommendation> recommendations) {
        StringBuilder recommendationsText = new StringBuilder();
        for (Recommendation rec : recommendations) {
            recommendationsText.append("Post ID: ").append(rec.getPostId())
                    .append("\nPlastic Type: ").append(rec.getPlasticType())
                    .append("\nItem Location: ").append(rec.getItemLocation())
                    .append("\nQuantity: ").append(rec.getQuantity())
                    .append("\nStarting Price: ").append(rec.getStartingPrice())
                    .append("\n\n");
        }
        recommendationsTextView.setText(recommendationsText.toString());
    }
}
