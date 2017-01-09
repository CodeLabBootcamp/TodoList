package com.codelab.todolist.RecyclerViewAdapters;

import com.codelab.todolist.Models.Task;
import com.codelab.todolist.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class TasksFirebaseAdapter extends FirebaseRecyclerAdapter<Task, TasksViewHolder> {

    /**
     * a constructor to initialize the adapter
     *
     * @param ref (the passing query/reference which the data will be pulled from)
     */
    public TasksFirebaseAdapter(Query ref) {
        super(Task.class, R.layout.item_task, TasksViewHolder.class, ref);
    }

    /**
     * a method that's called to populate teh ViewHolder, and fill it's content with
     * data from the model
     *
     * @param holder   (the view holder that's being populated)
     * @param task     (the task that's associated with this row)
     * @param position (the position of this row)
     */
    @Override
    protected void populateViewHolder(TasksViewHolder holder, final Task task, final int position) {
        // pass reference to the holder, so it can use it for manipulating the data row
        holder.setRef(getRef(position));
        // set title to TextView
        holder.title.setText(task.getTitle());
        // set checked status of the CheckBox
        holder.isDone.setChecked(task.isDone());
    }
}