package com.codelab.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codelab.todolist.Utilities.Consts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SniperDW on 12/19/2016.
 */

public class TasksFirebaseAdapter extends FirebaseRecyclerAdapter<Task, ViewHolder> {

    Context context;

    public TasksFirebaseAdapter(Context context, DatabaseReference ref) {
        super(Task.class, R.layout.item_task, ViewHolder.class, ref);
        this.context = context;
    }


    @Override
    protected void populateViewHolder(ViewHolder holder, final Task task, int position) {
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.isDone.setChecked(task.isDone());
    }

}
