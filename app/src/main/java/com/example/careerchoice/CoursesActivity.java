package com.example.careerchoice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careerchoice.models.CoursesModel;
import com.example.careerchoice.models.CoursesResponseModel;
import com.example.careerchoice.network.NetworkClient;
import com.example.careerchoice.network.NetworkService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesActivity extends AppCompatActivity {

    RecyclerView booksRecyclerView;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        textView = findViewById(R.id.text_toolbar_title_courses);
        textView.setText(getIntent().getStringExtra("field_name"));
        imageView = findViewById(R.id.image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        booksRecyclerView = (RecyclerView) findViewById(R.id.courses_recycler_view);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        booksRecyclerView.setHasFixedSize(true);
        getBooks();
    }

    private void getBooks() {

        final ProgressDialog progressDialog = new ProgressDialog(CoursesActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Getting field");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<CoursesResponseModel> getBooks = networkService.getCourses(getIntent().getStringExtra("field_name"));
        getBooks.enqueue(new Callback<CoursesResponseModel>() {
            @Override
            public void onResponse(Call<CoursesResponseModel> call, Response<CoursesResponseModel> response) {
                progressDialog.cancel();
                BooksAdapter booksAdapter = new BooksAdapter(response.body().getBooks());
                booksRecyclerView.setAdapter(booksAdapter);
            }

            @Override
            public void onFailure(Call<CoursesResponseModel> call, Throwable throwable) {
                progressDialog.cancel();
            }
        });
    }


    private class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

        List<CoursesModel> books;

        BooksAdapter(List<CoursesModel> books) {
            this.books = books;
        }


        @Override
        public int getItemCount() {
            return books.size();
        }

        @Override
        public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.courses_item_container, parent, false
            ));
        }

        @Override
        public void onBindViewHolder(final BookViewHolder holder, int position) {

            if (books.get(position).getCourses() != null && !books.get(position).getCourses().equals("")) {
                holder.textName.setText(books.get(position).getCourses());
            }
            /*if (books.get(position).getImage() != null && !books.get(position).getImage().equals("")) {
                Picasso.get().load(
                        books.get(position).getImage()).into(holder.imageBook);
            }*/
            else {
                Toast.makeText(CoursesActivity.this, "sorry not lod data", Toast.LENGTH_SHORT).show();
                holder.imageBook.setVisibility(View.GONE);
            }
            holder.cardBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), Courses_Details_Activity.class);
                    intent.putExtra("courses", books.get(holder.getAdapterPosition()).getCourses());
                    intent.putExtra("introduction", books.get(holder.getAdapterPosition()).getTime());
                    intent.putExtra("course_details", books.get(holder.getAdapterPosition()).getExam_type());
                    intent.putExtra("eligibility_criteria", books.get(holder.getAdapterPosition()).getEligibility_criteria());
                    intent.putExtra("admission", books.get(holder.getAdapterPosition()).getAdmission());
                    intent.putExtra("syllabus", books.get(holder.getAdapterPosition()).getSyllabus());
                    intent.putExtra("image", books.get(holder.getAdapterPosition()).getImage());
                    intent.putExtra("image_1", books.get(holder.getAdapterPosition()).getImage_1());
                    intent.putExtra("image_2", books.get(holder.getAdapterPosition()).getImage_2());
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
                imageBook = (ImageView) view.findViewById(R.id.image_book);
                cardBook = (CardView) view.findViewById(R.id.courses_card_view);
                textName = (TextView) view.findViewById(R.id.text_courses);
            }
        }
    }
}