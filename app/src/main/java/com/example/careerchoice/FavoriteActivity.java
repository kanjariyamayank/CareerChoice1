package com.example.careerchoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.careerchoice.database.DatabaseHandler;
import com.example.careerchoice.models.CollegeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static java.security.AccessController.getContext;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView favoriteRecyclerView;
    ImageView imageBack;
    TextView textTotalAmount;
    List<CollegeModel> favoriteItems;
    int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        textTotalAmount = (TextView) findViewById(R.id.text_total_amount);
        imageBack = (ImageView) findViewById(R.id.image_back);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        favoriteRecyclerView = findViewById(R.id.favorite_recycler_view);
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        favoriteRecyclerView.setHasFixedSize(true);
        favoriteItems = new DatabaseHandler(getApplicationContext()).getCartItems();
        favoriteRecyclerView.setAdapter(new BooksAdapter(favoriteItems));
    }

    private void calculateTotal() {
        totalAmount = 0;
        String s = "";
        for (int i = 0; i <= favoriteItems.size() - 1; i++) {
            totalAmount = totalAmount + Integer.parseInt(favoriteItems.get(i).getId());
        }

        textTotalAmount.setText("Total Amt. \u20B9 " + String.valueOf(totalAmount));

    }

    private class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

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
                    R.layout.favorite_item_container, parent, false
            ));
        }

        @Override
        public void onBindViewHolder(final BookViewHolder holder, int position) {


            if (books.get(position).getCollege_name() != null && !books.get(position).getCollege_name().equals("")) {
                holder.text_college_Name.setText(books.get(position).getCollege_name());
            } else {
                holder.text_college_Name.setVisibility(View.GONE);
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
                Picasso.with(getApplicationContext()).load(books.get(position).getImage_college()
                ).into(holder.imagecollege);
            } else {
                holder.imagecollege.setVisibility(View.GONE);
            }

            holder.textRemoveFromCart.setPaintFlags(holder.textRemoveFromCart.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            holder.textRemoveFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatabaseHandler(getApplicationContext()).removeItem(books.get(holder.getAdapterPosition()).getId());
                    favoriteItems.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(), favoriteItems.size());
                   /* calculateTotal();*/
                }
            });

        }

        class BookViewHolder extends RecyclerView.ViewHolder {

            ImageView imagecollege;
            TextView text_college_Name, textcontect, textaddress, textRemoveFromCart;

            BookViewHolder(View view) {
                super(view);

                imagecollege = (ImageView) view.findViewById(R.id.image_college_favorite);
                text_college_Name = (TextView) view.findViewById(R.id.text_college_name_favorite);
                textcontect = (TextView) view.findViewById(R.id.text_contect_favorite);
                textaddress = (TextView) view.findViewById(R.id.text_college_address_favorite);
                textRemoveFromCart = (TextView) view.findViewById(R.id.text_remove_item);
            }
        }
    }
}