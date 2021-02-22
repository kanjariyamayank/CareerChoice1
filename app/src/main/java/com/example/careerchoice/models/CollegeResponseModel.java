package com.example.careerchoice.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollegeResponseModel {

    @SerializedName("colleges")
    private List<CollegeModel> college;

    public List<CollegeModel> getCollegelist() {
        return college;
    }
}
