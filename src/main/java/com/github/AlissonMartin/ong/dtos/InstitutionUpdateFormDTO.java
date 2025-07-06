package com.github.AlissonMartin.ong.dtos;

import org.springframework.web.multipart.MultipartFile;

public class InstitutionUpdateFormDTO {
    private String full_name;
    private String name;
    private String description;
    private String federal_tax_id;
    private Long cnae;
    private String address;
    private Integer number;
    private String complement;
    private String district;
    private Integer zip;
    private Integer cityId;
    private Integer stateId;
    private String email;
    private String profilePhotoUrl;
    private String bannerUrl;
    private MultipartFile profilePhoto;
    private MultipartFile banner;

    // getters e setters
    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getFederal_tax_id() { return federal_tax_id; }
    public void setFederal_tax_id(String federal_tax_id) { this.federal_tax_id = federal_tax_id; }
    public Long getCnae() { return cnae; }
    public void setCnae(Long cnae) { this.cnae = cnae; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Integer getNumber() { return number; }
    public void setNumber(Integer number) { this.number = number; }
    public String getComplement() { return complement; }
    public void setComplement(String complement) { this.complement = complement; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public Integer getZip() { return zip; }
    public void setZip(Integer zip) { this.zip = zip; }
    public Integer getCityId() { return cityId; }
    public void setCityId(Integer cityId) { this.cityId = cityId; }
    public Integer getStateId() { return stateId; }
    public void setStateId(Integer stateId) { this.stateId = stateId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getProfilePhotoUrl() { return profilePhotoUrl; }
    public void setProfilePhotoUrl(String profilePhotoUrl) { this.profilePhotoUrl = profilePhotoUrl; }
    public String getBannerUrl() { return bannerUrl; }
    public void setBannerUrl(String bannerUrl) { this.bannerUrl = bannerUrl; }
    public MultipartFile getProfilePhoto() { return profilePhoto; }
    public void setProfilePhoto(MultipartFile profilePhoto) { this.profilePhoto = profilePhoto; }
    public MultipartFile getBanner() { return banner; }
    public void setBanner(MultipartFile banner) { this.banner = banner; }
}

