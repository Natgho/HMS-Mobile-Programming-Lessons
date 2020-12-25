package com.ylv.fragmentdemo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Sohbetler extends Fragment {
    private String baslik;

     public static Sohbetler newInstance(String baslik) {
        Bundle args = new Bundle();
        args.putString("BASLIK", baslik);
        Sohbetler fragment = new Sohbetler();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baslik = getArguments().getString("BASLIK");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sohbet, container, false);
        TextView title = view.findViewById(R.id.tvBaslik);
        title.setText(baslik);
        return view;
    }

    @Override
    public void onPause() {
        Toast.makeText(getContext(), "Sohbetler Duraklatıldı", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    public void onResume() {
        Toast.makeText(getContext(), "Sohbetler Devam ediyor", Toast.LENGTH_SHORT).show();
        super.onResume();
    }
}
