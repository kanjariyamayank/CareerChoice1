package com.example.careerchoice.network;

import com.example.careerchoice.models.CategoryResponseModel;
import com.example.careerchoice.models.CollegeResponseModel;
import com.example.careerchoice.models.CoursesResponseModel;
import com.example.careerchoice.models.FieldResponseModel;
import com.example.careerchoice.models.LoginResponseModel;
import com.example.careerchoice.models.RegistrationResponseModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkService {
    @FormUrlEncoded
    @POST("registration.php")
    Call<RegistrationResponseModel> register(@FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @POST("logincook.php")
    Call<LoginResponseModel> login(@Field("email") String email, @Field("password") String password);

    @POST("careerfield.php")
    Call<CategoryResponseModel> getCategoriesFromServer();

    @FormUrlEncoded
    @POST("choicefield.php")
    Call<FieldResponseModel> getBooksByCategories(@Field("field") String category);

    @FormUrlEncoded
    @POST("careercurses.php")
    Call<CoursesResponseModel> getCourses(@Field("field_name") String fieldname);

    @FormUrlEncoded
    @POST("colleges.php")
    Call<CollegeResponseModel> getCollege(@Field("field_name") String collegename);
}
