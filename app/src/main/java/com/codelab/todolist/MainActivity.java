package com.codelab.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.my_switch)
    Switch mySwitch;

    TasksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // adding listener to the switch to update adapter accordingly
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // update the value of filter in adapter
                adapter.setFilter(b);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // getting default shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String tasksJSON = preferences.getString("TASKS", "[]");

        // deserialize tasks
        ArrayList<Task> savedTasks = new Gson().fromJson(tasksJSON, new TypeToken<ArrayList<Task>>() {
        }.getType());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TasksAdapter(this, savedTasks, mySwitch.isChecked());
        recyclerView.setAdapter(adapter);

    }

    // open NewTaskActivity
    public void addTask(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        ArrayList<Task> tasks = adapter.getItems();

        // serialize tasks
        String tasksJSON = new Gson().toJson(tasks);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TASKS", tasksJSON);
        editor.apply();

        super.onStop();
    }


}
