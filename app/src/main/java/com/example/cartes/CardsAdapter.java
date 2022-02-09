package com.example.cartes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.cartes.databinding.LvCardsRowBinding;

import java.util.List;

public class CardsAdapter extends ArrayAdapter<Card> {
    public CardsAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Card> objects) {
        super(context, resource, textViewResourceId, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Card card = getItem(position);
        LvCardsRowBinding binding = null;
        if (convertView == null) {
            binding = LvCardsRowBinding.inflate(
                    LayoutInflater.from(getContext()),
                    parent,
                    false
            );
        } else {
            binding = LvCardsRowBinding.bind(convertView); // <2>
        }
        binding.txtTitleRow.setText(card.getName());
        binding.txtTraits.setText(card.getTraits());
        Glide.with(getContext()
        ).load("https://ringsdb.com/" + card.getImagesrc()
        ).into(binding.cardImageRow);
        System.out.println(card.toString());

        return binding.getRoot();
    }
}
