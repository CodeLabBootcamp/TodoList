package com.codelab.todolist.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codelab.todolist.R;
import com.codelab.todolist.RecyclerViewAdapters.TasksFirebaseAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        // getting reference to the "tasks" array in FireBase
        DatabaseReference myRef = database.getReference("tasks");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // initialize firebase adapter by passing context and reference
        TasksFirebaseAdapter adapter = new TasksFirebaseAdapter(this, myRef);
        recyclerView.setAdapter(adapter);

    }

    // open NewTaskActivity
    public void addTask(View view) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }
}
