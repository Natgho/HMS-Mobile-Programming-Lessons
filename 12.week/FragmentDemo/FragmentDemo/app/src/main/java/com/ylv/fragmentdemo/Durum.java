package com.ylv.fragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Durum extends Fragment {
    private String baslik;

    public static Durum newInstance(String baslik) {
        Bundle args = new Bundle();
        args.putString("BASLIK", baslik);
        Durum fragment = new Durum();
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
        View view = inflater.inflate(R.layout.layout_durum, container, false);
        TextView title = view.findViewById(R.id.tvBaslik);
        title.setText(baslik);
        return view;
    }
}
