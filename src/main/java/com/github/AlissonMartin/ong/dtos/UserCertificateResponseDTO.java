package com.github.AlissonMartin.ong.dtos;

import java.util.Date;

public class UserCertificateResponseDTO {
    private int id;
    private int userId;
    private int certificateId;
    private String certificateName;
    private Date acquiredAt;
    private Date expirationDate;

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCertificateId() { return certificateId; }
    public void setCertificateId(int certificateId) { this.certificateId = certificateId; }
    public String getCertificateName() { return certificateName; }
    public void setCertificateName(String certificateName) { this.certificateName = certificateName; }
    public Date getAcquiredAt() { return acquiredAt; }
    public void setAcquiredAt(Date acquiredAt) { this.acquiredAt = acquiredAt; }
    public Date getExpirationDate() { return expirationDate; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }
}

