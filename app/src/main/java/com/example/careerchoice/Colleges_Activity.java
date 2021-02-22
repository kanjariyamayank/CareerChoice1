package com.example.careerchoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.careerchoice.models.CollegeModel;
import com.example.careerchoice.models.CollegeResponseModel;
import com.example.careerchoice.models.CoursesModel;
import com.example.careerchoice.models.CoursesResponseModel;
import com.example.careerchoice.network.NetworkClient;
import com.example.careerchoice.network.NetworkService;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Colleges_Activity extends AppCompatActivity {
    RecyclerView collegeRecyclerView;
    ImageView imageView;
    TextView textView;
    BooksAdapter adapter;
    private List<CollegeModel> exampleListFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleges_);
        SlidrInterface slidrInterface = Slidr.attach(this);
        textView = findViewById(R.id.text_toolbar_title_college);
        textView.setText(getIntent().getStringExtra("field_name"));
        imageView = findViewById(R.id.image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        collegeRecyclerView = findViewById(R.id.college_recycler_view);
        collegeRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        collegeRecyclerView.setHasFixedSize(true);
        getBooks();
    }

    private void getBooks() {

        final ProgressDialog progressDialog = new ProgressDialog(Colleges_Activity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Getting field");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<CollegeResponseModel> getCollegelist = networkService.getCollege(getIntent().getStringExtra("field_name"));
        getCollegelist.enqueue(new Callback<CollegeResponseModel>() {
            @Override
            public void onResponse(Call<CollegeResponseModel> call, Response<CollegeResponseModel> response) {
                progressDialog.cancel();
                BooksAdapter booksAdapter = new BooksAdapter(response.body().getCollegelist());
                collegeRecyclerView.setAdapter(booksAdapter);
            }

            @Override
            public void onFailure(Call<CollegeResponseModel> call, Throwable throwable) {
                progressDialog.cancel();
            }
        });
    }

    private class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> implements Filterable {

        List<CollegeModel> books;

        BooksAdapter(List<CollegeModel> books) {
            this.books = books;
        }


        @Override
        public int getItemCount() {
            return books.size();
        }

        @Override
        public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.college_item_container, parent, false
            ));
        }

        @Override
        public void onBindViewHolder(final Colleges_Activity.BooksAdapter.BookViewHolder holder, int position) {

            if (books.get(position).getCollege_name() != null && !books.get(position).getCollege_name().equals("")) {
                holder.textcollegename.setText(books.get(position).getCollege_name());
            }
            if (books.get(position).getContact() != null && !books.get(position).getContact().equals("")) {
                holder.textcontect.setText(books.get(position).getContact());
            } else {
                holder.textcontect.setVisibility(View.GONE);
            }
            if (books.get(position).getAddress() != null && !books.get(position).getAddress().equals("")) {
                holder.textaddress.setText(books.get(position).getAddress());
            } else {
                holder.textaddress.setVisibility(View.GONE);
            }
            if (books.get(position).getImage_college() != null && !books.get(position).getImage_college().equals("")) {
                Picasso.with(getApplicationContext()).load(
                        books.get(position).getImage_college()).into(holder.imageBook);
            } else {
                Toast.makeText(Colleges_Activity.this, "sorry not lod data", Toast.LENGTH_SHORT).show();
                holder.imageBook.setVisibility(View.GONE);
            }
            holder.cardBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(getApplicationContext(), Colleges_Details_Activity.class);
                    intent.putExtra("id", books.get(holder.getAdapterPosition()).getId());
                    intent.putExtra("field_name", books.get(holder.getAdapterPosition()).getFieldname());
                    intent.putExtra("college_name", books.get(holder.getAdapterPosition()).getCollege_name());
                    intent.putExtra("college_code", books.get(holder.getAdapterPosition()).getCollege_code());
                    intent.putExtra("address", books.get(holder.getAdapterPosition()).getAddress());
                    intent.putExtra("contact", books.get(holder.getAdapterPosition()).getContact());
                    intent.putExtra("department", books.get(holder.getAdapterPosition()).getDepartment());
                    intent.putExtra("college_details", books.get(holder.getAdapterPosition()).getCollege_details());
                    intent.putExtra("image", books.get(holder.getAdapterPosition()).getImage_college());
                    intent.putExtra("image_1", books.get(holder.getAdapterPosition()).getImage_college_1());
                    intent.putExtra("image_2", books.get(holder.getAdapterPosition()).getImage_college_2());
                    intent.putExtra("college_link", books.get(holder.getAdapterPosition()).getCollege_link());
                    startActivity(intent);
                }
            });
        }

        @Override
        public Filter getFilter() {
            return exampleFilter;
        }

        private Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<CollegeModel> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(exampleListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (CollegeModel item : exampleListFull) {
                        if (item.getCollege_name().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                books.clear();
                books.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };

        class BookViewHolder extends RecyclerView.ViewHolder {

            CardView cardBook;
            ImageView imageBook;
            TextView textcollegename, textcontect, textaddress;

            BookViewHolder(View view) {
                super(view);
                imageBook = view.findViewById(R.id.image_college);
                cardBook = view.findViewById(R.id.college_card_view);
                textcollegename = view.findViewById(R.id.text_college_name);
                textcontect = view.findViewById(R.id.text_contect);
                textaddress = view.findViewById(R.id.text_address);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}