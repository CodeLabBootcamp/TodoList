package com.codelab.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SniperDW on 12/18/2016.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    Context context;
    ArrayList<Task> items;

    public TasksAdapter(Context context, ArrayList<Task> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //TODO do stuff here
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.is_done)
        CheckBox isDone;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
