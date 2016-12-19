package com.codelab.todolist.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.codelab.todolist.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by SniperDW on 12/19/2016.
 */

public class GsonHelper {

    public static void saveTasks(Context context, ArrayList<Task> tasks) {

        // serialize tasks
        String tasksJSON = new Gson().toJson(tasks);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TASKS", tasksJSON);
        editor.apply();

    }

    public static ArrayList<Task> loadTasks(Context context) {
        // getting default shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String tasksJSON = preferences.getString("TASKS", "[]");

        // deserialize tasks
        return new Gson().fromJson(tasksJSON, new TypeToken<ArrayList<Task>>() {
        }.getType());
    }

}
