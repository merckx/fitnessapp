package com.fitness.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fitness.R;

/**
 * Created by yani on 21.10.2016 г..
 */

public class MuscleGroupViewHolder extends RecyclerView.ViewHolder {

    public TextView nameTextView;

    public MuscleGroupViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.muscle_group_name);
    }



}
