package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        //Checking if Json is empty or not
        if (!json.isEmpty()) {

            try {

                //Creating the root object from the json
                JSONObject sandwich_details = new JSONObject(json);

                //Retrieving name Object from the root object
                JSONObject name = sandwich_details.getJSONObject("name");

                //Retrieving various data from the json
                String mainname = name.getString("mainName");
                String placeoforigin = sandwich_details.getString("placeOfOrigin");
                String description = sandwich_details.getString("description");
                String image = sandwich_details.getString("image");

                //Retrieving list of ingredients from the json
                JSONArray ingredients = sandwich_details.getJSONArray("ingredients");
                ArrayList<String> listOfIngredients = new ArrayList<>();
                for (int i = 0; i < ingredients.length(); i++) {
                    listOfIngredients.add(ingredients.getString(i));
                }

                //Retrieving list of otherNames from the json
                JSONArray alsoknownas = name.getJSONArray("alsoKnownAs");
                ArrayList<String> alternatename = new ArrayList<>();
                for (int i = 0; i < alsoknownas.length(); i++) {
                    alternatename.add(alsoknownas.getString(i));
                }

                //Returning the sandwich object
                return new Sandwich(mainname,alternatename,placeoforigin,description,image,listOfIngredients);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
