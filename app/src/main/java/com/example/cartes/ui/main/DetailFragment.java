package com.example.cartes.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cartes.Card;
import com.example.cartes.R;
import com.example.cartes.SharedViewModel;
import com.example.cartes.databinding.MainActivityBinding;
import com.example.cartes.databinding.MainFragment2Binding;

public class DetailFragment extends Fragment {

    private DetailViewModel mViewModel;
    private MainFragment2Binding binding;


    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragment2Binding.inflate(inflater);
        View view = binding.getRoot();


        Intent intent = getActivity().getIntent();


        if (intent != null) {
            Card card = (Card) intent.getSerializableExtra("card");

            if (card != null) {
                showData(card);
            }
        }

        SharedViewModel sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getSelected().observe(getViewLifecycleOwner(), this::showData);

        return view;
    }

    private void showData(Card card) {
        binding.txtTextDetail.setText(card.getName());
        binding.txtTextDetail.setText(card.getText());
        Glide.with(getContext()
        ).load("https://ringsdb.com/" + card.getImagesrc()
        ).into(binding.imgDetail);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        // TODO: Use the ViewModel
    }

}