package com.github.AlissonMartin.ong.dtos;

import java.util.Date;

public class UserCertificateCreateRequestDTO {
    private int userId;
    private String name;
    private Date acquiredAt;
    private Date expirationDate;

    // Getters e setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getAcquiredAt() { return acquiredAt; }
    public void setAcquiredAt(Date acquiredAt) { this.acquiredAt = acquiredAt; }
    public Date getExpirationDate() { return expirationDate; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }
}

