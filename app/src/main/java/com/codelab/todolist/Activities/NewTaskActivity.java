package com.codelab.todolist.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codelab.todolist.Models.Task;
import com.codelab.todolist.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    @BindView(R.id.edit)
    Button edit;
    @BindView(R.id.delete)
    Button delete;

    String key;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        ButterKnife.bind(this);


        key = getIntent().getStringExtra("KEY");
        // if key is null, then the action is ADD, if not, the action is EDIT
        if (key != null) {
            final DatabaseReference ref = database.getReference("tasks/" + key);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Task task = dataSnapshot.getValue(Task.class); // grab task from the dataSnapsho
                    title.setText(task.getTitle());
                    description.setText(task.getDescription());
                    deadline.setText(task.getDeadLine());
                    ref.removeEventListener(this); // this ensures that the data is only loaded once
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            // since KEY != null, then the operation is EDIT
            // so we will change the UI accordingly (hide add button and show edit and delete buttons)
            add.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
        }

    }

    public void addTask(View view) {
        Task newTask = new Task(title.getText().toString(), description.getText().toString(), deadline.getText().toString());
        DatabaseReference myRef = database.getReference("tasks");
        myRef.push().setValue(newTask);
        finish();
    }

    public void deleteTask(View view) {
        DatabaseReference myRef = database.getReference("tasks/" + key);
        myRef.removeValue();
        finish();
    }

    public void editTask(View view) {
        Task editedTask = new Task(title.getText().toString(), description.getText().toString(), deadline.getText().toString());
        DatabaseReference myRef = database.getReference("tasks/" + key);
        myRef.setValue(editedTask);
        finish();
    }
}
