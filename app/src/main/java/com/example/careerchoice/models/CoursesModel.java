package com.example.careerchoice.models;

import com.google.gson.annotations.SerializedName;

public class CoursesModel {

    @SerializedName("id")
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getId() {
        return id;
    }

    public String getFieldname() {
        return fieldname;
    }

    public String getCourses() {
        return courses;
    }

    @SerializedName("field_name")
    private String fieldname;

    @SerializedName("courses")
    private String courses;

    public void setTime(String time) {
        this.time = time;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getTime() {
        return time;
    }

    public String getExam_type() {
        return exam_type;
    }

    @SerializedName("introduction")
    private String time;

    @SerializedName("course_details")
    private String exam_type;

    @SerializedName("eligibility_criteria")
    private String eligibility_criteria;

    @SerializedName("admission")
    private String admission;

    public void setEligibility_criteria(String eligibility_criteria) {
        this.eligibility_criteria = eligibility_criteria;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getEligibility_criteria() {
        return eligibility_criteria;
    }

    public String getAdmission() {
        return admission;
    }

    public String getSyllabus() {
        return syllabus;
    }

    @SerializedName("syllabus")
    private String syllabus;

    @SerializedName("image")
    private String image;

    @SerializedName("image_1")
    private String image_1;

    public void setImage(String image) {
        this.image = image;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }

    public String getImage() {
        return image;
    }

    public String getImage_1() {
        return image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    @SerializedName("image_2")
    private String image_2;
}
