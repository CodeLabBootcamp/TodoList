package com.codelab.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    Context context;
    boolean filter;
    ArrayList<Task> items;
    ArrayList<Task> filteredTasks;

    public TasksAdapter(Context context, ArrayList<Task> items, boolean filter) {
        this.context = context;
        this.items = items;
        this.filter = filter;
        filter();
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
        filter();
    }

    public void addTask(Task task) {
        items.add(0, task);
        notifyDataSetChanged();
    }

    public ArrayList<Task> getItems() {
        return items;
    }

    private void filterAndNotify() {
        filter();
        notifyDataSetChanged();
    }

    private void filter() {
        filteredTasks = new ArrayList<>();
        for (Task item : items) {
            if (!item.isDone() || !filter)
                filteredTasks.add(item);
        }
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

            isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    filteredTasks.get(getLayoutPosition()).setDone(b);
                    filterAndNotify();
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
