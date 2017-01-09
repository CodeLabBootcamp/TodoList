package com.codelab.todolist.Models;

/**
 * Created by SniperDW on 12/18/2016.
 */

public class Task {

    private String title;
    private boolean done;

    public Task() {
    }

    public Task(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}