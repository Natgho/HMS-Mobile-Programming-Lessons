package com.tfkb.fanapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tfkb.fanapp.R;
import com.tfkb.fanapp.TeamDetailActivity;
import com.tfkb.fanapp.objects.Team;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private Context context;
    private ArrayList<Team> teams;
    private LayoutInflater inflater;

    public TeamAdapter(Context context, ArrayList<Team> teams) {
        this.context = context;
        this.teams = teams;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  inflater.inflate(R.layout.team_list_item,parent, false);
        TeamViewHolder viewHolder = new TeamViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        final Team team = teams.get(position);
        holder.teamLogo.setImageResource(team.getLogo());
        holder.teamName.setText(team.getName());

        holder.teamLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Team Logo", team.getLogo());
                bundle.putInt("Color First", team.getColorFirst());
                bundle.putInt("Color Second", team.getColorSecond());
                bundle.putString("Team Name", team.getName());
                bundle.putString("President", team.getPresident());
                bundle.putString("Stadium", team.getStadium());
                Intent intent = new Intent(context, TeamDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }


    public class TeamViewHolder extends RecyclerView.ViewHolder {
        private TextView teamName;
        private ImageView teamLogo;

        private TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.tvTeamName);
            teamLogo = itemView.findViewById(R.id.ivTeamLogo);
        }
    }
}
