package com.github.AlissonMartin.ong.dtos;

import java.util.Date;

public class PostSimpleResponseDTO {
    private int id;
    private String title;
    private String body;
    private String imageUrl;
    private Date createdAt;
    private Date deletedAt;

    public PostSimpleResponseDTO(int id, String title, String body, String imageUrl, Date createdAt, Date deletedAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getDeletedAt() { return deletedAt; }
    public void setDeletedAt(Date deletedAt) { this.deletedAt = deletedAt; }
}

