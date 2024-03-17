package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Recommendation> {

    private Context context;
    private List<Recommendation> posts;

    public PostAdapter(Context context, List<Recommendation> posts) {
        super(context, R.layout.post_item, posts);
        this.context = context;
        this.posts = posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Get the post for the current position
        Recommendation post = posts.get(position);

        // 2. Inflate the layout (if necessary)
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.post_item, parent, false);
        }

        // 3. Find the TextViews within your post_item.xml layout
        TextView postIdTextView = convertView.findViewById(R.id.postPostId);
        TextView plasticTypeTextView = convertView.findViewById(R.id.postPlasticType);
        TextView itemLocationTextView = convertView.findViewById(R.id.postItemLocation);
        TextView quantityTextView = convertView.findViewById(R.id.postQuantity);
        TextView startingPriceTextView = convertView.findViewById(R.id.postStartingPrice);


        // 4. Set the text of the TextViews
        postIdTextView.setText("Plastic Type: " + post.getPostId());
        plasticTypeTextView.setText("Quantity: " + post.getPlasticType());
        itemLocationTextView.setText("Quantity: " + post.getItemLocation());
        quantityTextView.setText("Quantity: " + post.getQuantity());
        startingPriceTextView.setText("Quantity: " + post.getStartingPrice());

        // 5. Return the completed view
        return convertView;
    }
}
