package com.example.cartes.ui.main;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cartes.Card;
import com.example.cartes.CardsAdapter;
import com.example.cartes.DetailActivity;
import com.example.cartes.R;
import com.example.cartes.SettingsActivity;
import com.example.cartes.SharedViewModel;
import com.example.cartes.databinding.MainFragmentBinding;


import java.util.ArrayList;


public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ArrayList<Card> items;
    private CardsAdapter adapter;

//https://youtu.be/4Kax7eqeRZg?t=4575

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MainFragmentBinding binding = MainFragmentBinding.inflate(inflater);
        View view = binding.getRoot();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String trait = preferences.getString("trait", "");


        items = new ArrayList<>();

        adapter = new CardsAdapter(
                getContext(),
                R.layout.lv_cards_row,
                R.id.txtTitleRow,
                items
        );

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getCards(trait).observe(getViewLifecycleOwner(), cards -> {
            adapter.clear();
            adapter.addAll(cards);
        });

        SharedViewModel sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);

        binding.lvCards.setAdapter(adapter);

        binding.lvCards.setOnItemClickListener((parent, view1, position, id) -> {
            Card card = adapter.getItem(position);
            if (!esTablet()) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("card", card);
                startActivity(i);
            }else{
                sharedViewModel.select(card);
            }



        });


        return view;
    }


    void refresh() {
        mViewModel.reload();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override //Apartados del menu de la derecha
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.refresh) {

            refresh();
        }

        if (id == R.id.settings) {
            Intent i = new Intent(getContext(), SettingsActivity.class);
            startActivity(i);

            refresh();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String trait = preferences.getString("trait", "");

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getCards(trait).removeObservers(getViewLifecycleOwner());

        mViewModel.getCards(trait).observe(getViewLifecycleOwner(), cards -> {
            adapter.clear();
            adapter.addAll(cards);
        });

    }

    boolean esTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }
}