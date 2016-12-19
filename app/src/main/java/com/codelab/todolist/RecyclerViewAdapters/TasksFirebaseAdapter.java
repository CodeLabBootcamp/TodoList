package com.codelab.todolist.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;

import com.codelab.todolist.Activities.NewTaskActivity;
import com.codelab.todolist.Models.Task;
import com.codelab.todolist.R;
import com.codelab.todolist.Utilities.Consts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by SniperDW on 12/19/2016.
 */

public class TasksFirebaseAdapter extends FirebaseRecyclerAdapter<Task, TasksViewHolder> {

    Context context;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public TasksFirebaseAdapter(Context context, DatabaseReference ref) {
        super(Task.class, R.layout.item_task, TasksViewHolder.class, ref);
        this.context = context;
    }


    @Override
    protected void populateViewHolder(TasksViewHolder holder, final Task task, final int position) {
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.deadline.setText(task.getDeadLine());
        holder.isDone.setChecked(task.isDone());

        holder.isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String key = getRef(position).getKey();
                task.setDone(b);
                DatabaseReference ref = database.getReference("tasks/" + key);
                ref.setValue(task);
            }
        });

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewTaskActivity.class);
                intent.putExtra(Consts.KEY, getRef(position).getKey());
                context.startActivity(intent);
            }
        });
    }

}
