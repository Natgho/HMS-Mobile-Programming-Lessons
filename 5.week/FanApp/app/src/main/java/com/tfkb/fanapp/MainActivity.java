package com.tfkb.fanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.tfkb.fanapp.adapters.TeamAdapter;
import com.tfkb.fanapp.objects.Team;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvTeams;
    private ArrayList<Team> teams;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTeams = findViewById(R.id.rvTeamList);
        teams = new ArrayList<>();
        teams.add(new Team("Fenerbahçe", R.drawable.fb, R.color.yellow, R.color.nigh_blue, 0,"Ali Koç", "Şükrü Saraçoğlu"));
        teams.add(new Team("Galatsaray", R.drawable.gs, R.color.yellow, R.color.red, 0,"Mustafa Cengiz", "Türktelekom Arena"));
        teams.add(new Team("Beşiktaş", R.drawable.bjk, R.color.black, R.color.white, R.color.red,"Ahmet Nur Çebi", "İnönü Stadı"));
        teams.add(new Team("Trabzonspor", R.drawable.ts, R.color.bordo, R.color.blue, 0,"Ahmet Ağaoğlu", "Şenol Güneş Stadı"));
        teams.add(new Team("Başakşehir SK", R.drawable.bsk, R.color.blue, R.color.white, R.color.orange,"Göksel Gümüşdağ", "Fatih Terim Stadı"));
        teams.add(new Team("Sivasspor", R.drawable.svs, R.color.red, R.color.white, 0,"Ali Koç", " Sivas Stadı"));
        teams.add(new Team("Kayserispor", R.drawable.kys, R.color.red, R.color.yellow, 0,"Ali Koç", "Kayseri Stadı"));
        teams.add(new Team("Antalyaspor", R.drawable.ant, R.color.red, R.color.white, 0,"Ali Koç", "Antalya Stadı"));
        adapter = new TeamAdapter(this, teams);
        rvTeams.setAdapter(adapter);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));



    }

}
