package com.mkr.aameclibrary.Admin.ui.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.SplashActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookIssuePopWindowActivity extends AppCompatActivity {
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
        sharedPrefManager=new SharedPrefManager(BookIssuePopWindowActivity.this);
        bookName=findViewById(R.id.popWindowBookName);
        bookAuthor=findViewById(R.id.popWindowBookAuthor);
        bookPublisher=findViewById(R.id.popWindowBookPublishers);
        issueButton=findViewById(R.id.popWindowBookIssueButton);
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
                IssuedBookDetails issuedBookDetails=new IssuedBookDetails(String.valueOf(sharedPrefManager.details().getRegno()),name,author,publisher,currentDate,sharedPrefManager.details().getName(),"Nil",pushId,"Not returned yet");
                dc_ref.setValue(issuedBookDetails);
                finish();
                Toast.makeText(getApplicationContext(),"Book Issued",Toast.LENGTH_SHORT).show();
            }
        });

    }
}