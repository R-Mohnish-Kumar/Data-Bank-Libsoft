package com.mkr.aameclibrary.Admin.ui.book_holders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkr.aameclibrary.Admin.ui.books.IssuedBookDetails;
import com.mkr.aameclibrary.R;

import java.util.ArrayList;

public class BookHoldersRecyclerAdapter extends RecyclerView.Adapter<BookHoldersRecyclerAdapter.ViewHolder> {
    ArrayList<IssuedBookDetails> bookDetails= new ArrayList<>();
    Context context;

    public BookHoldersRecyclerAdapter(ArrayList<IssuedBookDetails> bookDetails, Context context) {
        this.bookDetails = bookDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public BookHoldersRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_holder_row_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHoldersRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        IssuedBookDetails issuedBookDetails= bookDetails.get(position);
        holder.issuerName.setText(issuedBookDetails.getIssuerName());
        holder.bookName.setText(issuedBookDetails.getTitle());
        holder.author.setText(issuedBookDetails.getAuthor());
        holder.publisher.setText(issuedBookDetails.getPublisher());
        holder.dateOfIssue.setText(issuedBookDetails.getDateOfIssue());
        holder.issuerId.setText(issuedBookDetails.getIssuerId());
        holder.dateOfReturn.setText(issuedBookDetails.getDateOfReturn());
        holder.status.setText(issuedBookDetails.getStatus());

    }

    @Override
    public int getItemCount() {
        return bookDetails.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView issuerName,bookName,author,dateOfIssue,publisher,issuerId,dateOfReturn,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateOfReturn=itemView.findViewById(R.id.holderBooksReturnDate);
            status=itemView.findViewById(R.id.holderBooksStatus);
            issuerId=itemView.findViewById(R.id.booksHoldersId);
            issuerName=itemView.findViewById(R.id.booksHoldersName);
            bookName=itemView.findViewById(R.id.booksHoldersBookName);
            author=itemView.findViewById(R.id.booksIssuedAuthorName);
            dateOfIssue=itemView.findViewById(R.id.booksIssuedDate);
            publisher=itemView.findViewById(R.id.booksIssuedPublisher);

        }
    }
}
