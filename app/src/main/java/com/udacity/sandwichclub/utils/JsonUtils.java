package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        if (!json.equals("")) {

            try {
                JSONObject sandwich_details = new JSONObject(json);
                JSONObject name = sandwich_details.getJSONObject("name");
                String mainname = name.getString("mainName");
                String placeoforigin = sandwich_details.getString("placeOfOrigin");
                String description = sandwich_details.getString("description");
                String image = sandwich_details.getString("image");

                JSONArray ingredients = sandwich_details.getJSONArray("ingredients");
                ArrayList<String> listOfIngredients = new ArrayList<>();
                for (int i = 0; i < ingredients.length(); i++) {
                    listOfIngredients.add(ingredients.getString(i));
                }

                JSONArray alsoknownas = name.getJSONArray("alsoKnownAs");
                ArrayList<String> alternatename = new ArrayList<>();
                for (int i = 0; i < alsoknownas.length(); i++) {
                    alternatename.add(alsoknownas.getString(i));
                }

                Sandwich sandwich = new Sandwich();
                sandwich.setImage(image);
                sandwich.setDescription(description);
                sandwich.setMainName(mainname);
                sandwich.setPlaceOfOrigin(placeoforigin);
                sandwich.setAlsoKnownAs(alternatename);
                sandwich.setIngredients(listOfIngredients);

                return sandwich;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
