package com.tfkb.fanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class TeamDetailActivity extends AppCompatActivity {
    private ImageView teamLogo;
    private TextView teamName, president, stadium;
    private View viewFirst, viewSecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        teamLogo = findViewById(R.id.ivTeamLogo);
        teamName = findViewById(R.id.tvTeamName);
        president = findViewById(R.id.tvTeamPresident);
        stadium = findViewById(R.id.tvTeamStadium);
        viewFirst = findViewById(R.id.viewColorFirst);
        viewSecond = findViewById(R.id.viewColorSecond);


        Bundle extras = getIntent().getExtras();

        teamLogo.setImageResource(extras.getInt("Team Logo"));
        teamLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale));

        teamName.setText(extras.getString("Team Name"));
        president.setText(extras.getString("President"));
        stadium.setText(extras.getString("Stadium"));
        viewFirst.setBackgroundColor(getResources().getColor(extras.getInt("Color First")));
        viewSecond.setBackgroundColor(getResources().getColor(extras.getInt("Color Second")));




    }
}
