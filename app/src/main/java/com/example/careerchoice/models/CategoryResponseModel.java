package com.example.careerchoice.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponseModel {

    @SerializedName("careerfield")
    private List<CategoryModel> categories;

    public List<CategoryModel> getCategories() {
        return categories;
    }

}
