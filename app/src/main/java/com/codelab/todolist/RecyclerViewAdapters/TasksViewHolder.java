package com.codelab.todolist.RecyclerViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.codelab.todolist.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /**
     * Declaring views and preparing them for binding using {@link ButterKnife}
     */
    @BindView(R.id.root)
    CardView root;
    @BindView(R.id.is_done)
    CheckBox isDone;
    @BindView(R.id.title)
    TextView title;

    /**
     * Declare {@link FirebaseDatabase} globally for easy accessibility
     */
    private DatabaseReference ref;
    /**
     * Declare {@link Context} globally for easy accessibility
     */
    private Context context;

    /**
     * a constructor to initialize the {@link TasksViewHolder}
     *
     * @param v (the view that was inflated from the xml layout, which will be used for view binding)
     */
    public TasksViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);

        // get context from the view (all views has context) and assign it to the global xontext
        context = v.getContext();

        // set OnClickListener to the entire layout
        v.setOnClickListener(this);

        // set OnCheckedChangeListener to the CheckBox
        isDone.setOnCheckedChangeListener(this);

    }

    /**
     * set {@link DatabaseReference} to be used for manipulating data in this class
     *
     * @param ref (the {@link DatabaseReference} to this task in the database
     */
    public void setRef(DatabaseReference ref) {
        this.ref = ref;
    }

    /**
     * onClick callback because the class implements {@link View.OnClickListener}
     *
     * @param v (the view that was clicked)
     */
    @Override
    public void onClick(View v) {
        openTaskDialog(title.getText().toString());
    }

    /**
     * onCheckedChanged callback because the class implements {@link CompoundButton.OnCheckedChangeListener}
     *
     * @param buttonView (the CheckBox that changed its checked status)
     * @param isChecked  (the new checked status of the CheckBox)
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // call updateTaskStatus
        updateTaskStatus(isChecked);
    }

    /**
     * update the value of the task in the database
     *
     * @param isDone (the new status of the task)
     */
    private void updateTaskStatus(boolean isDone) {
        // update the child node "done" of the task
        ref.child("done").setValue(isDone);
    }

    /**
     * open dialog to edit the
     *
     * @param title (the current title of the task)
     */
    private void openTaskDialog(final String title) {
        // initialize the dialog
        new MaterialDialog.Builder(context)
                // set dialog title
                .title(R.string.edit_task)
                // choosing the dialog of type input
                // passing (hint, prefill, allowEmptyInput, callback)
                .input(context.getString(R.string.enter_your_task_here), title,
                        false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // call editTask
                                editTask(ref, input.toString());
                            }
                        })
                // set positiveText
                .positiveText(R.string.edit)
                // set negativeText
                .negativeText(R.string.delete)
                // set click callback for negative button
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        // call deleteTask
                        deleteTask(ref);
                    }
                })
                // show dialog
                .show();

    }

    /**
     * delete task from database
     *
     * @param ref (reference to the task to be deleted)
     */
    private void deleteTask(DatabaseReference ref) {
        ref.removeValue();
    }

    /**
     * update task in database
     *
     * @param ref   (reference to the task to be updated)
     * @param title (the task's new title)
     */
    private void editTask(DatabaseReference ref, String title) {
        ref.child("title").setValue(title);
    }

}