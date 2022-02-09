package com.example.cartes;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDao {
    @Query("select * from card where traits like '%' || :trait || '%' ")
    LiveData<List<Card>> getCards(String trait);

    @Insert
    void addCard(Card card);

    @Insert
    void addCards(List<Card> cards);

    @Delete
    void deleteCard(Card card);

    @Query("DELETE FROM card")
    void deleteCards();


}