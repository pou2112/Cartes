package com.example.cartes;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CardsApiClient {

    public ArrayList<Card> getCards() {
        String BASE_URL = "https://ringsdb.com/api/public/";


        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .build();
        String url = builtUri.toString();
        ArrayList<Card> response = doCall(url);

        return response;
    }

    private ArrayList<Card> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return parseJson(JsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Card> parseJson(String jsonResponse) {
       ArrayList<Card> cards =  new ArrayList<Card>();
        try {
            JSONArray array = new JSONArray(jsonResponse);

            for (int i = 0; i < array.length(); i++) {
                Card card = new Card();
                JSONObject object = array.getJSONObject(i);
                card.setName(object.getString("name"));
                card.setText(object.getString("text"));
                card.setTraits(object.getString("traits"));
                card.setImagesrc(object.getString("imagesrc"));

                cards.add(card);

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }

        return cards;
    }
}
