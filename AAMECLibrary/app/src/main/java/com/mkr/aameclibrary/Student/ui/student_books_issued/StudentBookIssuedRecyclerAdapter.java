package com.mkr.aameclibrary.Student.ui.student_books_issued;

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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mkr.aameclibrary.Admin.ui.books.BooksRecyclerAdapter;
import com.mkr.aameclibrary.Admin.ui.books.IssuedBookDetails;
import com.mkr.aameclibrary.BookDetails;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.SplashActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentBookIssuedRecyclerAdapter extends RecyclerView.Adapter<StudentBookIssuedRecyclerAdapter.ViewHolder> {
    ArrayList<IssuedBookDetails> bookDetailsArrayList = new ArrayList<>();
    Context context;
    DatabaseReference databaseReference;
    SharedPrefManager sharedPrefManager= new SharedPrefManager(context);

    public StudentBookIssuedRecyclerAdapter(ArrayList<IssuedBookDetails> bookDetailsArrayList, Context context) {
        this.bookDetailsArrayList=bookDetailsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentBookIssuedRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.books_issued_row_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentBookIssuedRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        IssuedBookDetails bookDetails= bookDetailsArrayList.get(position);
        holder.bookName.setText(bookDetails.getTitle());
        holder.author.setText(bookDetails.getAuthor());
        holder.publisher.setText(bookDetails.getPublisher());
        holder.dateOfIssue.setText(bookDetails.getDateOfIssue());
        holder.dateOfReturn.setText(bookDetails.getDateOfReturn());
        holder.status.setText(bookDetails.getStatus());

        holder.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bookDetails.getStatus().equals("Book Returned")){
                    HashMap returnBookDetails=new HashMap();
                    returnBookDetails.put("author",bookDetails.getAuthor());
                    returnBookDetails.put("dateOfIssue",bookDetails.getDateOfIssue());
                    returnBookDetails.put("issuerId",bookDetails.getIssuerId());
                    returnBookDetails.put("issuerName",bookDetails.getIssuerName());
                    returnBookDetails.put("publisher",bookDetails.getPublisher());
                    returnBookDetails.put("title",bookDetails.getTitle());
                    returnBookDetails.put("pushId",bookDetails.getPushId());
                    returnBookDetails.put("status","Book Returned");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String currentDate=dtf.format(now).substring(0,10);
                    returnBookDetails.put("dateOfReturn",currentDate);
                    String key=bookDetails.getPushId();
                    Log.e("Key",key);
                    databaseReference= FirebaseDatabase.getInstance().getReference().child("BookIssued");
                    databaseReference.child(bookDetails.getPushId()).updateChildren(returnBookDetails).addOnCompleteListener(new OnCompleteListener() {
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
        TextView bookName,author,dateOfIssue,publisher,status,dateOfReturn;
        Button returnButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateOfReturn=itemView.findViewById(R.id.booksReturnDate);
            status=itemView.findViewById(R.id.booksStatus);
            returnButton=itemView.findViewById(R.id.booksIssuedReturnButton);
            bookName=itemView.findViewById(R.id.titleBooksIssued);
            author=itemView.findViewById(R.id.booksIssuedAuthorName);
            publisher=itemView.findViewById(R.id.booksIssuedPublisher);
            dateOfIssue=itemView.findViewById(R.id.booksIssuedDate);
        }
    }
}
