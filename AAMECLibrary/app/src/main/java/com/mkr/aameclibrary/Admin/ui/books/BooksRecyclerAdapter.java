package com.mkr.aameclibrary.Admin.ui.books;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.mkr.aameclibrary.BookDetails;
import com.mkr.aameclibrary.R;

import java.util.ArrayList;

public class BooksRecyclerAdapter extends RecyclerView.Adapter<BooksRecyclerAdapter.ViewHolder> {
    ArrayList<BookDetails> BookDetails;
    Context context;

    public BooksRecyclerAdapter(ArrayList<BookDetails> bookDetails, Context context) {
        this.BookDetails=bookDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public BooksRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.books_row_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BookDetails bookDetails= BookDetails.get(position);
        holder.bookTitle.setText(bookDetails.getTitle_of_the_book());
        holder.bookAuthor.setText(bookDetails.getAuthors());


    }

    @Override
    public int getItemCount() {
        return BookDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle,bookAuthor;
        CardView layoutCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutCardView=itemView.findViewById(R.id.cardViewLayoutBooks);
            bookTitle=itemView.findViewById(R.id.titleBooks);
            bookAuthor=itemView.findViewById(R.id.authorName);
        }
    }
}
