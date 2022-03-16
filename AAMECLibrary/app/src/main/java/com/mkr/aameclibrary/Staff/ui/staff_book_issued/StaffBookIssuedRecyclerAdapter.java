package com.mkr.aameclibrary.Staff.ui.staff_book_issued;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mkr.aameclibrary.Admin.ui.books.BookIssuePopWindowActivity;
import com.mkr.aameclibrary.Admin.ui.books.IssuedBookDetails;
import com.mkr.aameclibrary.BuildConfig;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.SplashActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class StaffBookIssuedRecyclerAdapter extends RecyclerView.Adapter<StaffBookIssuedRecyclerAdapter.ViewHolder> {
    ArrayList<IssuedBookDetails> bookDetailsArrayList = new ArrayList<>();
    Context context;
    DatabaseReference databaseReference;
    SharedPrefManager sharedPrefManager= new SharedPrefManager(context);

    public StaffBookIssuedRecyclerAdapter(ArrayList<IssuedBookDetails> bookDetailsArrayList, Context context) {
        this.bookDetailsArrayList = bookDetailsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffBookIssuedRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.books_issued_row_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffBookIssuedRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        IssuedBookDetails issuedBookDetails= bookDetailsArrayList.get(position);
        holder.bookName.setText(issuedBookDetails.getTitle());
        holder.author.setText(issuedBookDetails.getAuthor());
        holder.publisher.setText(issuedBookDetails.getPublisher());
        holder.dateOfIssue.setText(issuedBookDetails.getDateOfIssue());
        holder.dateOfReturn.setText(issuedBookDetails.getDateOfReturn());
        holder.status.setText(issuedBookDetails.getStatus());
        holder.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!issuedBookDetails.getStatus().equals("Book Returned")){
                    HashMap returnBookDetails=new HashMap();
                    returnBookDetails.put("author",issuedBookDetails.getAuthor());
                    returnBookDetails.put("dateOfIssue",issuedBookDetails.getDateOfIssue());
                    returnBookDetails.put("issuerId",issuedBookDetails.getIssuerId());
                    returnBookDetails.put("issuerName",issuedBookDetails.getIssuerName());
                    returnBookDetails.put("publisher",issuedBookDetails.getPublisher());
                    returnBookDetails.put("title",issuedBookDetails.getTitle());
                    returnBookDetails.put("pushId",issuedBookDetails.getPushId());
                    returnBookDetails.put("status","Book Returned");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDate=dtf.format(now).substring(0,10);
                    returnBookDetails.put("dateOfReturn",currentDate);
                    String key=issuedBookDetails.getPushId();
                    Log.e("Key",key);
                    databaseReference= FirebaseDatabase.getInstance().getReference().child("BookIssued");
                    databaseReference.child(issuedBookDetails.getPushId()).updateChildren(returnBookDetails).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            Toast.makeText(context,"Book returned",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context,"Unable to return the book",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    holder.returnButton.setEnabled(false);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return bookDetailsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookName,author,dateOfIssue,publisher,dateOfReturn,status;
        Button returnButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status=itemView.findViewById(R.id.booksStatus);
            returnButton=itemView.findViewById(R.id.booksIssuedReturnButton);
            dateOfReturn=itemView.findViewById(R.id.booksReturnDate);
            bookName=itemView.findViewById(R.id.titleBooksIssued);
            author=itemView.findViewById(R.id.booksIssuedAuthorName);
            dateOfIssue=itemView.findViewById(R.id.booksIssuedDate);
            publisher=itemView.findViewById(R.id.booksIssuedPublisher);

        }
    }
}
