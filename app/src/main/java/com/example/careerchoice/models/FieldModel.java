package com.example.careerchoice.models;

import com.google.gson.annotations.SerializedName;

public class FieldModel {
    @SerializedName("id")
    private String id;

    @SerializedName("field")
    private String category;

    @SerializedName("field_name")
    private String name;

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }
}
