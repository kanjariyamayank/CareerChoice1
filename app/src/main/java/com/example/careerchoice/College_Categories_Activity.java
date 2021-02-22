package com.example.careerchoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.careerchoice.models.CategoryModel;
import com.example.careerchoice.models.CategoryResponseModel;
import com.example.careerchoice.network.NetworkClient;
import com.example.careerchoice.network.NetworkService;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class College_Categories_Activity extends AppCompatActivity {
    RecyclerView collegecategoriesRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college__categories_);
        SlidrInterface slidrInterface = Slidr.attach(this);
        swipeRefreshLayout = findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCategories();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        findViewById(R.id.image_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        collegecategoriesRecyclerView = findViewById(R.id.college_categories_recycler_view);
        collegecategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        collegecategoriesRecyclerView.setHasFixedSize(true);
        getCategories();
    }

    private void getCategories() {


        final ProgressDialog progressDialog = new ProgressDialog(College_Categories_Activity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Getting categories");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<CategoryResponseModel> categoryResponseModelCall = networkService.getCategoriesFromServer();

        categoryResponseModelCall.enqueue(new Callback<CategoryResponseModel>() {

            @Override
            public void onResponse(@NonNull Call<CategoryResponseModel> call, @NonNull Response<CategoryResponseModel> response) {
                Toast.makeText(College_Categories_Activity.this, "success", Toast.LENGTH_SHORT).show();
                CategoryResponseModel categoryResponseModel = response.body();
                final College_Categories_Activity.CategoriesAdapter categoriesAdapter = new College_Categories_Activity.CategoriesAdapter(categoryResponseModel.getCategories());
                collegecategoriesRecyclerView.setAdapter(categoriesAdapter);
                progressDialog.cancel();
            }

            @Override
            public void onFailure(@NonNull Call<CategoryResponseModel> call, @NonNull Throwable t) {
                Toast.makeText(College_Categories_Activity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        });
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        CardView categoryItemLayout;
        TextView textCategory;

        CategoryViewHolder(View view) {
            super(view);
            categoryItemLayout = (CardView) view.findViewById(R.id.category_card_view);
            textCategory = (TextView) view.findViewById(R.id.text_category);
        }
    }

    private class CategoriesAdapter extends RecyclerView.Adapter<College_Categories_Activity.CategoryViewHolder> {

        List<CategoryModel> categories;

        CategoriesAdapter(List<CategoryModel> categories) {
            this.categories = categories;
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        @Override
        public College_Categories_Activity.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new College_Categories_Activity.CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_container, parent, false));
        }

        @Override
        public void onBindViewHolder(final College_Categories_Activity.CategoryViewHolder holder, int position) {

            if (categories.get(holder.getAdapterPosition()).getCategory() != null) {
                holder.textCategory.setText(categories.get(holder.getAdapterPosition()).getCategory());

                holder.categoryItemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), CollegeFieldsActivity.class);
                        intent.putExtra("field", categories.get(holder.getAdapterPosition()).getCategory());
                        startActivity(intent);
                    }
                });
            }
        }
    }
}