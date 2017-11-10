package com.example.anas.jsonsample;

/**
 * Created by anas on 10/11/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anas.jsonsample.model.JsonAnswer;
import com.example.anas.jsonsample.model.Result;

import java.util.List;

public class StandingsResultsAdapter extends RecyclerView.Adapter<StandingsResultsAdapter.MyViewHolder> {

    private List<Result> results;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.desc);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result result = results.get(position);
        holder.title.setText(result.getPlayerName());
        holder.desc.setText(result.getEntryName());
    }

    @Override
    public int getItemCount() {
        if (results == null) return 0;
        return results.size();
    }

    public void updateAnswers(JsonAnswer answer) {
        if (answer != null) {
            results = answer.getStandings().getResults();
            notifyDataSetChanged();
        }
    }
}