package com.example.cartes.ui.main;

import android.app.Application;
import android.os.Looper;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cartes.AppDatabase;
import com.example.cartes.Card;
import com.example.cartes.CardDao;
import com.example.cartes.CardsApiClient;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel {

    private final Application app;
    private final AppDatabase appDatabase;
    private final CardDao cardDao;
    private LiveData<List<Card>> cards;

    public MainViewModel(Application application){
        super(application);

        this.app = application;
        this.appDatabase = AppDatabase.getDatabase(
                this.getApplication());
        this.cardDao = appDatabase.getCardDao();
    }

    public LiveData<List<Card>> getCards(String trait){
        return cardDao.getCards(trait);
    }


    public void reload(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            CardsApiClient api = new CardsApiClient();
            ArrayList<Card> cards = api.getCards();
            cardDao.deleteCards();
            cardDao.addCards(cards);
        });



    }


}