package com.codelab.todolist.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.codelab.todolist.Models.Task;
import com.codelab.todolist.R;
import com.codelab.todolist.RecyclerViewAdapters.TasksFirebaseAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    /**
     * Declaring views and preparing them for binding using {@link ButterKnife}
     */
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tabs)
    TabLayout mTabs;

    /**
     * Declare {@link FirebaseDatabase} globally for easy accessibility
     */
    FirebaseDatabase database;

    /**
     * {@link #onCreate(Bundle)} is the first method to be activated
     * in the activity's lifecycle, and it will be where we'll bind and setup views
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // initialize the global FirebaseDatabase instance
        database = FirebaseDatabase.getInstance();

        // call setupTabs() method to setup the TabLayout
        setupTabs();

        // call setupRecyclerView() method to setup the RecyclerView
        setupRecyclerView();
    }

    /**
     * {@link #setupTabs()} setups the {@link TabLayout} by adding the necessary tabs
     * and attaching an {@link TabLayout.OnTabSelectedListener} to the layout intercept tab
     * changing events and excute actions accordingly
     */
    private void setupTabs() {
        // adding tabs for All, Ongoing and Done tasks
        mTabs.addTab(mTabs.newTab().setText(R.string.all));
        mTabs.addTab(mTabs.newTab().setText(R.string.ongoing));
        mTabs.addTab(mTabs.newTab().setText(R.string.done));

        // adding the listener
        mTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // when a tab is selected, setupRecyclerView(int) is called
                // to refresh the recyclerView according to the newly selected
                // filter
                setupRecyclerView(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // ignored
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // ignored
            }
        });
    }

    /**
     * a default method which calls {@link #setupRecyclerView(int)} with value 0
     * referring to the ALL tasks filter
     */
    private void setupRecyclerView() {
        setupRecyclerView(0);
    }

    /**
     * get {@link DatabaseReference} and {@link Query}, and pass it to {@link TasksFirebaseAdapter}
     * <p>
     * setup {@link #recyclerView} and attach the adapter to it
     *
     * @param tab (the filter type, which corresponds with the selected tab position)
     *            0 = ALL
     *            1 = Ongoing (done = false)
     *            2 = Done (done = true)
     */

    private void setupRecyclerView(int tab) {

        // get reference to the tasks node in our database
        DatabaseReference myRef = database.getReference("tasks");

        // declare the query variable
        Query query;

        // switch according to the selected tab
        switch (tab) {
            case 0: // 0 = all filter, query remains as is
                query = myRef;
                break;
            case 1: // 1 = ongoing, filter is set to done = false
                query = myRef.orderByChild("done").equalTo(false);
                break;
            case 2: // 2 = done, filter is set to done = true
                query = myRef.orderByChild("done").equalTo(true);
                break;
            default: // default case, in ensure null safety
                query = myRef;
                break;
        }

        // declare and initialize the adapter and pass the query to its constructor
        TasksFirebaseAdapter adapter = new TasksFirebaseAdapter(query);

        // set LinearLayoutManager(Context) for vertical lists
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // attach the adapter to the recyclerView
        recyclerView.setAdapter(adapter);
    }

    /**
     * a method to open task adding dialog, it's called by FAB via its onClick XML attribute
     *
     * @param view (the view that was clicked)
     */
    public void openAddTaskDialog(View view) {
        // initialize the dialog
        new MaterialDialog.Builder(this)
                // set dialog title
                .title(R.string.add_task)
                // choosing the dialog of type input
                // passing (hint, prefill, allowEmptyInput, callback)
                .input(R.string.enter_your_task_here, 0, false, new MaterialDialog.InputCallback() {

                    // this method is called after the positive button is clicked
                    // and it passes the text that was entered
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        // pass the entered text to the addTask method
                        addTask(input.toString());
                    }
                })
                // set positiveText
                .positiveText(R.string.add)
                // show dialog
                .show();

    }

    /**
     * a method to add a new task to firebase database
     *
     * @param title (the title of the task)
     */
    private void addTask(String title) {

        // declare and initialize task, and pass the title to its constructor
        Task task = new Task(title);

        // get reference to the tasks node in our database
        DatabaseReference myRef = database.getReference("tasks");

        // push a new task to the array
        myRef.push().setValue(task);
    }
}