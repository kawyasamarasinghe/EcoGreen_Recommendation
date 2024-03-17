package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// ... Import your data structures, API interface, and NetworkClient ...

public class NewRecommendationActivity extends AppCompatActivity {

    private Spinner plasticTypeSpinner;
    private Spinner itemLocationSpinner;
    private Spinner industrySpinner;
    private Button getRecommendationsButton;
    private TextView recommendationsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recommendation);

        plasticTypeSpinner = findViewById(R.id.plasticTypeSpinner);
        itemLocationSpinner = findViewById(R.id.itemLocationSpinner);
        industrySpinner = findViewById(R.id.industrySpinner);
        getRecommendationsButton = findViewById(R.id.getRecommendationsButton);
        recommendationsTextView = findViewById(R.id.recommendationsTextView);
        getRecommendationsButton.setOnClickListener(this::fetchRecommendations);

// Plastic Type Spinner

        ArrayAdapter<CharSequence> plasticTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.plastic_types, android.R.layout.simple_spinner_item);
        plasticTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plasticTypeSpinner.setAdapter(plasticTypeAdapter);


// Item Location Spinner

        ArrayAdapter<CharSequence> itemLocationAdapter = ArrayAdapter.createFromResource(this,
                R.array.item_locations, android.R.layout.simple_spinner_item);
        itemLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemLocationSpinner.setAdapter(itemLocationAdapter);

// Industry Spinner

        ArrayAdapter<CharSequence> industryAdapter = ArrayAdapter.createFromResource(this,
                R.array.industries, android.R.layout.simple_spinner_item);
        industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        industrySpinner.setAdapter(industryAdapter);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the current activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchRecommendations(View view) {
        String plasticType = plasticTypeSpinner.getSelectedItem().toString();
        String itemLocation = itemLocationSpinner.getSelectedItem().toString();
        String industry = industrySpinner.getSelectedItem().toString();

        NewUserInput userInput = new NewUserInput();
        userInput.setPlasticType(plasticType);
        userInput.setItemLocation(itemLocation);
        userInput.setIndustry(industry);

        RecommendationAPI api = NetworkClient.getRetrofitInstance().create(RecommendationAPI.class);
        Call<List<Recommendation>> call = api.getRecommendationsForNewUser(userInput);

        call.enqueue(new Callback<List<Recommendation>>() {
            @Override
            public void onResponse(Call<List<Recommendation>> call, Response<List<Recommendation>> response) {
                if (response.isSuccessful()) {
                    List<Recommendation> recommendations = response.body();
                    displayRecommendations(recommendations);
                } else {
                    Toast.makeText(NewRecommendationActivity.this, "Error fetching recommendations", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Recommendation>> call, Throwable t) {
                Toast.makeText(NewRecommendationActivity.this, "Network Failure", Toast.LENGTH_SHORT).show();
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
