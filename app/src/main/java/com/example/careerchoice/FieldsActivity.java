package com.example.careerchoice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.example.careerchoice.models.FieldModel;
import com.example.careerchoice.models.FieldResponseModel;
import com.example.careerchoice.network.NetworkClient;
import com.example.careerchoice.network.NetworkService;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FieldsActivity extends AppCompatActivity {

    RecyclerView booksRecyclerView;
    TextView textToolbarTitle;
    ImageView imageBack;
    /*SwipeRefreshLayout swipeRefreshLayout;*/
    PullRefreshLayout pullRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);
        SlidrInterface slidrInterface = Slidr.attach(this);
        pullRefreshLayout = findViewById(R.id.refresh);
       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBooks();
                swipeRefreshLayout.setRefreshing(false);
            }
        });*/
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {

               if (pullRefreshLayout!= null){
                   getBooks();
                   pullRefreshLayout.setRefreshing(false);

               }else {
               }
           }
       });
        textToolbarTitle = (TextView) findViewById(R.id.text_toolbar_title_field);
        textToolbarTitle.setText(getIntent().getStringExtra("field"));

        imageBack = (ImageView) findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        booksRecyclerView = (RecyclerView) findViewById(R.id.Fields_recycler_view);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        booksRecyclerView.setHasFixedSize(true);
        getBooks();
    }

    private void getBooks() {

        final ProgressDialog progressDialog = new ProgressDialog(FieldsActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Getting field");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<FieldResponseModel> getBooks = networkService.getBooksByCategories(getIntent().getStringExtra("field"));
        getBooks.enqueue(new Callback<FieldResponseModel>() {
            @Override
            public void onResponse(Call<FieldResponseModel> call, Response<FieldResponseModel> response) {
                progressDialog.cancel();
                BooksAdapter booksAdapter = new BooksAdapter(response.body().getBooks());
                booksRecyclerView.setAdapter(booksAdapter);
            }

            @Override
            public void onFailure(Call<FieldResponseModel> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(FieldsActivity.this,"Check your connection",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

        List<FieldModel> books;

        BooksAdapter(List<FieldModel> books) {
            this.books = books;
        }


        @Override
        public int getItemCount() {
            return books.size();
        }

        @Override
        public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.field_item_container, parent, false
            ));
        }

        @Override
        public void onBindViewHolder(final BookViewHolder holder, int position) {

            if (books.get(position).getName() != null && !books.get(position).getName().equals("")) {
                holder.textName.setText(books.get(position).getName());
            } else {
                holder.textName.setVisibility(View.GONE);
            }
            holder.cardBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CoursesActivity.class);
                    intent.putExtra("field_name", books.get(holder.getAdapterPosition()).getName());
                    startActivity(intent);
                }
            });
        }

        class BookViewHolder extends RecyclerView.ViewHolder {

            CardView cardBook;
            ImageView imageBook;
            TextView textName, textAuthor, textPrice, textDescription;

            BookViewHolder(View view) {
                super(view);

                cardBook = (CardView) view.findViewById(R.id.field_card_view);
                textName = (TextView) view.findViewById(R.id.text_fields);
            }
        }
    }

}