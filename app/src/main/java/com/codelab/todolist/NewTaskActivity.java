package com.codelab.todolist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ButterKnife.bind(this);
    }

    public void addTask(View view) {

        Gson gson = new Gson();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String tasksJSON = preferences.getString("TASKS", "[]");

        ArrayList<Task> savedTasks = gson.fromJson(tasksJSON, new TypeToken<ArrayList<Task>>() {
        }.getType());

        savedTasks.add(0, new Task(title.getText().toString(), description.getText().toString(), deadline.getText().toString()));

        tasksJSON = gson.toJson(savedTasks);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TASKS", tasksJSON);
        editor.apply();

        finish();

    }
}
