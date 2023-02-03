package com.app.quickTask.entity;

import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Task {

    private int id;
    @Size(min = 5, message = "Description should be at least 5 characters")
    private String description;
    private LocalDate completionDate;
    private boolean complete;

    public Task(){}

    public Task(String description, boolean complete) {
        this.description = description;
        this.complete = complete;
    }

    public Task(int id, String description, LocalDate completionDate, boolean complete) {
        this.id = id;
        this.description = description;
        this.completionDate = completionDate;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", completionDate='" + completionDate + '\'' +
                ", isCompleted=" + complete +
                '}';
    }
}
