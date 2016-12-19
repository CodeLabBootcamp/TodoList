package com.codelab.todolist.Models;

/**
 * Created by SniperDW on 12/18/2016.
 */

public class Task {

    private String title;
    private String description;
    private String deadLine;
    private boolean done;

    public Task() {
    }

    public Task(String title, String description, String deadLine) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
