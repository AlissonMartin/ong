package com.github.AlissonMartin.ong.dtos;

import org.springframework.web.multipart.MultipartFile;

public class RegisterInstitutionFormDTO {
    private String full_name;
    private String name;
    private String federal_tax_id;
    private String address;
    private Integer number;
    private String complement;
    private String district;
    private Integer zip;
    private Integer city_id;
    private Integer state_id;
    private String email;
    private MultipartFile profilePhoto;
    private MultipartFile banner;

    // getters e setters
    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFederal_tax_id() { return federal_tax_id; }
    public void setFederal_tax_id(String federal_tax_id) { this.federal_tax_id = federal_tax_id; }
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
    public Integer getCity_id() { return city_id; }
    public void setCity_id(Integer city_id) { this.city_id = city_id; }
    public Integer getState_id() { return state_id; }
    public void setState_id(Integer state_id) { this.state_id = state_id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public MultipartFile getProfilePhoto() { return profilePhoto; }
    public void setProfilePhoto(MultipartFile profilePhoto) { this.profilePhoto = profilePhoto; }
    public MultipartFile getBanner() { return banner; }
    public void setBanner(MultipartFile banner) { this.banner = banner; }
}

