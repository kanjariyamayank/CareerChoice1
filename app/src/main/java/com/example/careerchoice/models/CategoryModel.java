package com.example.careerchoice.models;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {


    @SerializedName("field")
    private String category;

    public String getCategory() {
        return category;
    }
}
