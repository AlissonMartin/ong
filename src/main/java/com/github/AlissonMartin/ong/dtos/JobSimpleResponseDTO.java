package com.github.AlissonMartin.ong.dtos;

import java.util.Date;

public class JobSimpleResponseDTO {
    private int id;
    private String title;
    private String description;
    private Date createdAt;
    private Date deletedAt;

    public JobSimpleResponseDTO(int id, String title, String description, Date createdAt, Date deletedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Date deletedAt) { this.deletedAt = deletedAt; }
}

