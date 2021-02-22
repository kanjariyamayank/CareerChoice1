package com.example.careerchoice.models;

import com.google.gson.annotations.SerializedName;

public class CollegeModel {

    @SerializedName("id")
    private String id;

    @SerializedName("field_name")
    private String fieldname;

    @SerializedName("college_name")
    private String college_name;

    @SerializedName("college_code")
    private String college_code;

    @SerializedName("address")
    private String address;

    @SerializedName("contact")
    private String contact;

    @SerializedName("image")
    private String 	image_college;

    @SerializedName("department")
    private String department;

    @SerializedName("college_details")
    private String college_details;

    @SerializedName("image_1")
    private String image_college_1;

    @SerializedName("college_link")
    private String college_link;

    public String getCollege_link() {
        return college_link;
    }

    public void setCollege_link(String college_link) {
        this.college_link = college_link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getCollege_code() {
        return college_code;
    }

    public void setCollege_code(String college_code) {
        this.college_code = college_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage_college() {
        return image_college;
    }

    public void setImage_college(String image_college) {
        this.image_college = image_college;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCollege_details() {
        return college_details;
    }

    public void setCollege_details(String college_details) {
        this.college_details = college_details;
    }

    public String getImage_college_1() {
        return image_college_1;
    }

    public void setImage_college_1(String image_college_1) {
        this.image_college_1 = image_college_1;
    }

    public String getImage_college_2() {
        return image_college_2;
    }

    public void setImage_college_2(String image_college_2) {
        this.image_college_2 = image_college_2;
    }

    @SerializedName("image_2")
    private String image_college_2;
}
