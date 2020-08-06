package com.example.careerchoice.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldResponseModel {

    @SerializedName("choicefield")
    private List<FieldModel> books;

    public List<FieldModel> getBooks() {
        return books;
    }

}
