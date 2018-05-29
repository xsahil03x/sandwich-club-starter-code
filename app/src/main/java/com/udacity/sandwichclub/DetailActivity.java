package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        //Populating the UI from the data retrieved
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        String noData = "No Data Available";

        //Referencing various textViews
        TextView name = findViewById(R.id.name_tv);
        TextView alternateName = findViewById(R.id.othernames_tv);
        TextView origin = findViewById(R.id.origin_tv);
        TextView description = findViewById(R.id.description_tv);
        TextView ingredients = findViewById(R.id.ingredients_tv);

        //Setting up the various fields
        name.setText(sandwich.getMainName());
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            origin.setText(noData);
        } else {
            origin.setText(sandwich.getPlaceOfOrigin());
        }
        description.setText(sandwich.getDescription());

        if (sandwich.getAlsoKnownAs().isEmpty()) {
            alternateName.setText(noData);
        } else {
            StringBuilder names = new StringBuilder();
            for (String value : sandwich.getAlsoKnownAs()) {
                names.append(value).append(", ");
            }
            alternateName.setText(names);
        }
        StringBuilder ingredient = new StringBuilder();
        for (String value : sandwich.getIngredients()) {
            ingredient.append("\u2022 ").append(value).append("\n");
        }
        ingredients.setText(ingredient);
    }
}
