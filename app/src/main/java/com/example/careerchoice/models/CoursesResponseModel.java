package com.example.careerchoice.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoursesResponseModel {

    @SerializedName("careercourses")
    private List<CoursesModel> books;

    public List<CoursesModel> getBooks() {
        return books;
    }
}
