package com.mkr.aameclibrary.Staff.ui.staff_book_issued;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mkr.aameclibrary.Admin.ui.books.BookIssuePopWindowActivity;
import com.mkr.aameclibrary.Admin.ui.books.IssuedBookDetails;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.SplashActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StaffBookIssuePopWindowActivity extends AppCompatActivity {
    TextView bookName,bookAuthor,bookPublisher;
    Button issueButton;
    DatabaseReference databaseReference;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issue_pop_window);

        DisplayMetrics displayMetrics= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        double width=displayMetrics.widthPixels;
        double height=displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.46));
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);
        sharedPrefManager=new SharedPrefManager(StaffBookIssuePopWindowActivity.this);
        bookName=findViewById(R.id.staffPopWindowBookName);
        bookAuthor=findViewById(R.id.staffPopWindowBookAuthor);
        bookPublisher=findViewById(R.id.staffPopWindowBookPublishers);
        issueButton=findViewById(R.id.staffPopWindowBookIssueButton);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String author=intent.getStringExtra("author");
        String publisher=intent.getStringExtra("publisher");
        bookName.setText(name);
        bookAuthor.setText(author);
        bookPublisher.setText(publisher);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentDate=dtf.format(now).substring(0,10);


        databaseReference= FirebaseDatabase.getInstance().getReference().child("BookIssued");

        issueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference db=firebaseDatabase.getReference().child("BookIssued");
                DatabaseReference data = db;
                DatabaseReference dc_ref=data.push();
                String pushId=dc_ref.getKey();
                IssuedBookDetails issuedBookDetails=new IssuedBookDetails(String.valueOf(sharedPrefManager.staffDetails().getStaffId()),name,author,publisher,currentDate,sharedPrefManager.staffDetails().getName(),"Nil",pushId,"Not returned yet");
                dc_ref.setValue(issuedBookDetails);
                finish();
                Toast.makeText(getApplicationContext(),"Book Issued",Toast.LENGTH_SHORT).show();
            }
        });

    }
}