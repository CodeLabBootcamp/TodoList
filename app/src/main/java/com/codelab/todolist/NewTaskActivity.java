package com.codelab.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codelab.todolist.Utilities.Consts;
import com.codelab.todolist.Utilities.GsonHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewTaskActivity extends AppCompatActivity {

    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.deadline)
    EditText deadline;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.delete)
    Button delete;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ButterKnife.bind(this);


        position = getIntent().getIntExtra(Consts.POSITION, -1);

        if (position != -1) {

            ArrayList<Task> tasks = GsonHelper.loadTasks(this);
            Task task = tasks.get(position);

            title.setText(task.getTitle());
            description.setText(task.getDescription());
            deadline.setText(task.getDeadLine());
            add.setText(R.string.edit);
            delete.setVisibility(View.VISIBLE);
        }

    }

    public void addTask(View view) {


        ArrayList<Task> savedTasks = GsonHelper.loadTasks(this);

        Task newTask = new Task(title.getText().toString(), description.getText().toString(), deadline.getText().toString());

        if (position != -1) {
            savedTasks.remove(position);
            savedTasks.add(position, newTask);
        } else {
            savedTasks.add(0, newTask);
        }
        GsonHelper.saveTasks(this, savedTasks);

        finish();

    }

    public void deleteTask(View view) {

        ArrayList<Task> savedTasks = GsonHelper.loadTasks(this);
        savedTasks.remove(position);
        GsonHelper.saveTasks(this, savedTasks);

        finish();



    }
}
