package com.codelab.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codelab.todolist.Utilities.Consts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    Context context;
    boolean filter; // show all tasks or undone tasks
    ArrayList<Task> items; // list of all tasks
    ArrayList<Task> filteredTasks; // list of viewed tasks

    public TasksAdapter(Context context, ArrayList<Task> items, boolean filter) {
        this.context = context;
        this.items = items;
        this.filter = filter;
        filter();
    }

    // change filter value
    public void setFilter(boolean filter) {
        this.filter = filter;
        filter();
    }

    public void addTask(Task task) {
        items.add(0, task);
        notifyDataSetChanged();
    }

    // return items for saving
    public ArrayList<Task> getItems() {
        return items;
    }

    // filter items
    private void filter() {
        filteredTasks = new ArrayList<>();
        for (Task item : items) {
            if (!item.isDone() || !filter) // if filter is ON, then all items are added
                filteredTasks.add(item);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 100);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Task task = filteredTasks.get(position);

        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.isDone.setChecked(task.isDone());

    }

    @Override
    public int getItemCount() {
        return filteredTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.root)
        LinearLayout root;
        @BindView(R.id.is_done)
        CheckBox isDone;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;

        public ViewHolder(View v) {
            super(v);

            // THE RIGHT WAY
            ButterKnife.bind(this, v);

            // on check listener to update the model
            isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    filteredTasks.get(getLayoutPosition()).setDone(b);
                    filter();
                }
            });

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewTaskActivity.class);
                    intent.putExtra(Consts.POSITION, getLayoutPosition());
                    context.startActivity(intent);
                }
            });

/*          THE OLD WAY
            isDone = (CheckBox) v.findViewById(R.id.is_done);
            title = (TextView) v.findViewById(R.id.title);
            description = (TextView) v.findViewById(R.id.description);
*/

        }

    }
}
