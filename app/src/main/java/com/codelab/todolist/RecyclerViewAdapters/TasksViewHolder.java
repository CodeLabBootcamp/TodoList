package com.codelab.todolist.RecyclerViewAdapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.codelab.todolist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.root)
    CardView root;
    @BindView(R.id.is_done)
    CheckBox isDone;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.deadline)
    TextView deadline;

    public TasksViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

}
