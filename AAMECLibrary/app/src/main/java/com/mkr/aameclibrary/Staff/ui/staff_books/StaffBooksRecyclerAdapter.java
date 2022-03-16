package com.mkr.aameclibrary.Staff.ui.staff_books;

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

import com.mkr.aameclibrary.Admin.ui.books.BookIssuePopWindowActivity;
import com.mkr.aameclibrary.Admin.ui.books.BooksRecyclerAdapter;
import com.mkr.aameclibrary.BookDetails;
import com.mkr.aameclibrary.R;

import java.util.ArrayList;

public class StaffBooksRecyclerAdapter extends RecyclerView.Adapter<StaffBooksRecyclerAdapter.ViewHolder> {
    ArrayList<com.mkr.aameclibrary.BookDetails> BookDetails;
    Context context;

    public StaffBooksRecyclerAdapter(ArrayList<BookDetails> bookDetails, Context context) {
        this.BookDetails=bookDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffBooksRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.books_row_item,parent,false);
        return new StaffBooksRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffBooksRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BookDetails bookDetails= BookDetails.get(position);
        holder.bookTitle.setText(bookDetails.getTitle_of_the_book());
        holder.bookAuthor.setText(bookDetails.getAuthors());
        holder.layoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BookIssuePopWindowActivity.class);
                intent.putExtra("name",bookDetails.getTitle_of_the_book());
                intent.putExtra("author",bookDetails.getAuthors());
                intent.putExtra("publisher",bookDetails.getPublishers());
                context.startActivity(intent);
            }
        });


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
