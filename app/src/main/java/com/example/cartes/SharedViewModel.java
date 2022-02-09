package com.example.cartes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel{
    private final MutableLiveData<Card> selected = new MutableLiveData<Card>();

    public void select(Card card) {
        selected.setValue(card);
    }



    public LiveData<Card> getSelected(){
        return selected;
    }

}
