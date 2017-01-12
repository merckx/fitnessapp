package com.fitness.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.R;
import com.fitness.model.MuscleGroup;
import com.fitness.model.MuscleGroupViewHolder;

import java.util.List;

/**
 * Created by yani on 10.1.2017 г..
 */

public class MuscleGroupAdapter extends RecyclerView.Adapter<MuscleGroupViewHolder> {

    private List<MuscleGroup> muscleGroups;

    public MuscleGroupAdapter(List<MuscleGroup> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    @Override
    public com.fitness.model.MuscleGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_muscle_group, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MuscleGroupViewHolder vh = new MuscleGroupViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(com.fitness.model.MuscleGroupViewHolder holder, int position) {
        holder.nameTextView.setText(muscleGroups.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return muscleGroups.size();
    }

}
