package com.tasktracker.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String status;
    private String credat;
    private String moddat;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCredat() {
        return credat;
    }

    public void setCredat(String credat) {
        this.credat = credat;
    }

    public String getModdat() {
        return moddat;
    }

    public void setModdat(String moddat) {
        this.moddat = moddat;
    }

    public Task(int id, String description, String status, String credat, String moddat) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.credat = credat;
        this.moddat = moddat;
    }
}
