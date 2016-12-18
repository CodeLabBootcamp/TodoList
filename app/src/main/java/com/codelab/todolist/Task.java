package com.codelab.todolist;

import java.util.Date;

/**
 * Created by SniperDW on 12/18/2016.
 */

public class Task {

    private String title;
    private String description;
    private Date createdAt;
    private Date deadLine;
    private boolean done;

    public Task(String title) {
        this.title = title;
    }

    public Task() {
    }

    public Task(String title, String description, Date createdAt, Date deadLine, boolean done) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.deadLine = deadLine;
        this.done = done;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
