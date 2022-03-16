package com.mkr.aameclibrary.Staff.ui.staff_book_holders;

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

public class StaffBookHoldersRecyclerAdapter extends RecyclerView.Adapter<StaffBookHoldersRecyclerAdapter.ViewHolder> {
    ArrayList<IssuedBookDetails> bookDetailsArrayList= new ArrayList<>();
    Context context;

    public StaffBookHoldersRecyclerAdapter(ArrayList<IssuedBookDetails> bookDetailsArrayList, Context context) {
        this.bookDetailsArrayList = bookDetailsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffBookHoldersRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_holder_row_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffBookHoldersRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        IssuedBookDetails issuedBookDetails=bookDetailsArrayList.get(position);
        holder.issuerName.setText(issuedBookDetails.getIssuerName());
        holder.bookName.setText(issuedBookDetails.getTitle());
        holder.author.setText(issuedBookDetails.getAuthor());
        holder.publisher.setText(issuedBookDetails.getPublisher());
        holder.dateOfIssue.setText(issuedBookDetails.getDateOfIssue());
        holder.issuerId.setText(issuedBookDetails.getIssuerId());
        

    }

    @Override
    public int getItemCount() {
        return bookDetailsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView issuerName,bookName,author,dateOfIssue,publisher,issuerId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            issuerId=itemView.findViewById(R.id.booksHoldersId);
            issuerName=itemView.findViewById(R.id.booksHoldersName);
            bookName=itemView.findViewById(R.id.booksHoldersBookName);
            author=itemView.findViewById(R.id.booksIssuedAuthorName);
            dateOfIssue=itemView.findViewById(R.id.booksIssuedDate);
            publisher=itemView.findViewById(R.id.booksIssuedPublisher);

        }
    }
}
