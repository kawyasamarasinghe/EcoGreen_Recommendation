package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CSVImporter {
    private Context context;

    public CSVImporter(Context context) {
        this.context = context;
    }

    public void importCSV() {
        try {
            InputStream inputStream = context.getAssets().open("dataset.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String[] row;
            int rowNum = 0;

            while ((row = reader.readNext()) != null) {
                try {
                    if (rowNum > 0) {
                        Map<String, Object> post = new HashMap<>();
                        post.put("userId", row[0]);
                        post.put("postId", row[1]);
                        post.put("plasticType", row[2]);
                        post.put("itemLocation", row[3]);
                        post.put("quantity", row[4]);
                        post.put("startingPrice", row[5]);
                        post.put("industry", row[6]);
                        post.put("userInteract", row[7]);

                        db.collection("posts").add(post)
                                .addOnSuccessListener(documentReference -> {
                                    Log.d("CSVImport", "Document added successfully!");
                                })
                                .addOnFailureListener(e -> {
                                    Log.e("CSVImport", "Error adding document", e);
                                });
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.e("CSVImport", "Error processing row " + rowNum + ": " + e.getMessage());
                }
                rowNum++;
            }
        } catch (IOException | CsvValidationException e) {
            Log.e("CSVImport", "Error reading CSV file", e);
        }
    }
}
