package com.example.cartes;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class RefreshDataTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        CardsApiClient api = new CardsApiClient();

        ArrayList<Card> cards = api.getCards();

        Log.d(null,cards.toString());

        return null;
    }
}
